/*
Assignment 4 
Thomas Kistler
11/7/17
*/

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <vector>

// For the CUDA runtime routines (prefixed with "cuda_")
#include <cuda_runtime.h>

using namespace std;

__shared__ float SUM;//overall sum

int MAX;//max node

/**
 * CUDA Kernel Device code
 *
 * Computes the clustering coefficient of vector adjVector
 */
__global__ void clusterCoefficient(int * adjVector, int numElements) {
   
   int index = threadIdx.x + blockIdx.x * blockDim.x;
   float result = 0;
   float Ni = 0;
   float Mi = 0;
   vector<int> neighbors;
   //calculate degree (Ni)
   for(int i = index; i < index + MAX; i++)
   {
	if(adjVector[index] == 1)
	{
	   Ni += 1.0;//add to degree of current node	
	   //get neighbors list during degree count
           neighbors.push_back(index);	
	}
   }
   //calculate edges between neighbors (Mi)
   for(int i = 0; i < neighbors.size(); i++)
   {
	for(int j = 0; j < neighbors.size(); j++)
	{
	    /* add 1 to the edge count if the two neighbors share an edge 
	       meaning there's a 1 at the correct index                   */
	    if( i != j && adjVector[ neighbors[j] * MAX + neighbors[i] ] == 1)
		    Mi += 1.0;
	}
   }

   //calculate clustering coefficient
   float result = (2 * Mi) / (Ni * (Ni - 1)); 
   atomicAdd(SUM, result);//add result, atomically
}

/**
 * Host main routine
 */
int main(int argc, char* argv[]){
    
    /* Read in file */
    cout<<"Please enter the input file name"<<endl;
    string infile;
    cin>>infile;
    fstream myfile(infile, std::ios_base::in);
    int u, v;
    int maxNode = 0;
    vector<pair<int,int> > allEdges;
    while(myfile >> u >> v)
    {
        allEdges.push_back(make_pair(u,v));
        if(u > maxNode)
          maxNode = u;

        if(v > maxNode)
          maxNode = v;                 
    }

    MAX = maxNode;
    // Error code to check return values for CUDA calls
    cudaError_t err = cudaSuccess;
    int threadsPerBlock = 1;
    int numElements = maxNode * maxNode;
    cout<<"Graph has " << numElements <<" nodes "<<endl;

    size_t size = numElements * sizeof(int);

    // Allocate the host input vector A
    int *h_A = (int *)malloc(size);

    // Verify that allocations succeeded
    if (h_A == NULL )
    {
        fprintf(stderr, "Failed to allocate host vector!\n");
        exit(EXIT_FAILURE);
    }
    
    //populate the adjacency vector 
    for(int i = 0; i < allEdges.size(); i++){
       u = allEdges[i].first;
       v = allEdges[i].second;
       h_A[(i * u) + v] = 1;  //using 1D vector instead of 2D matrix
    }

    // Allocate the device input vector A
    int *d_A = NULL;
    err = cudaMalloc((void **)&d_A, size);

    if (err != cudaSuccess)
    {
        fprintf(stderr, "Failed to allocate device vector A (error code %s)!\n", cudaGetErrorString(err));
        exit(EXIT_FAILURE);
    }

    // Copy the host input vector into host memory to the device 
    printf("Copy input data from the host memory to the CUDA device\n");
    err = cudaMemcpy(d_A, h_A, size, cudaMemcpyHostToDevice);

    if (err != cudaSuccess)
    {
        fprintf(stderr, "Failed to copy vector from host to device (error code %s)!\n", cudaGetErrorString(err));
        exit(EXIT_FAILURE);
    }

    int blocksPerGrid = numElements;//assuming threadsPerBlock = 1
    //launch kernel
    printf("CUDA kernel launch with %d blocks of %d threads\n", blocksPerGrid, threadsPerBlock);

    //call kernel
    clusterCoefficient<<<blocksPerGrid, threadsPerBlock>>>(d_A, numElements);

    err = cudaGetLastError();

    if (err != cudaSuccess)
    {
        fprintf(stderr, "Failed to launch clusterCoefficient kernel (error code %s)!\n", cudaGetErrorString(err));
        exit(EXIT_FAILURE);
    }

    // Free device global memory
    err = cudaFree(d_A);

    if (err != cudaSuccess)
    {
        fprintf(stderr, "Failed to free device vector A (error code %s)!\n", cudaGetErrorString(err));
        exit(EXIT_FAILURE);
    }

    // Free host memory
    free(h_A);

    // Reset the device and exit
    // cudaDeviceReset causes the driver to clean up all state. While
    // not mandatory in normal operation, it is good practice.  It is also
    // needed to ensure correct operation when the application is being
    // profiled. Calling cudaDeviceReset causes all profile data to be
    // flushed before the application exits
    err = cudaDeviceReset();

    if (err != cudaSuccess)
    {
        fprintf(stderr, "Failed to deinitialize the device! error=%s\n", cudaGetErrorString(err));
        exit(EXIT_FAILURE);
    }
    
    //result
    float CC = SUM / numElements;
    cout<<endl<<"The clustering coefficient for the graph is: "<<cc<<endl<<endl;

    printf("Done\n");
    return 0;
}











