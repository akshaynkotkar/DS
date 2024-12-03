package MPIAssign;

import mpi.MPI;

public class ScatterGather {
    public static void main(String args[]) {
        // Initialize MPI execution environment
        MPI.Init(args);

        // Get the id of the process
        int rank = MPI.COMM_WORLD.Rank();
        // Total number of processes is stored in size
        int size = MPI.COMM_WORLD.Size();
        int root = 0;

        // Array which will be filled with data by the root process
        int[] sendbuf = new int[size];

        // Create data to be scattered
        if (rank == root) {
            sendbuf[0] = 10;
            sendbuf[1] = 20;
            sendbuf[2] = 30;
            sendbuf[3] = 40;

            // Print current process number
            System.out.print("Processor " + rank + " has data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }

        // Array to collect data in recvbuf
        int[] recvbuf = new int[1];

        // Scatter the data from root to all processes
        MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT, recvbuf, 0, 1, MPI.INT, root);

        // Print the received data and double it
        System.out.println("Processor " + rank + " has data: " + recvbuf[0]);
        recvbuf[0] = recvbuf[0] * 2;

        // Gather the modified data back to the root process
        MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.INT, sendbuf, 0, 1, MPI.INT, root);

        // Display the gathered result at the root process
        if (rank == root) {
            System.out.println("Process 0 has data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }

        // Terminate MPI execution
        MPI.Finalize();
    }
}
