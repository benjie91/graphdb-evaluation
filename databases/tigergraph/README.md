# TigerGraph DB

## Setting up
### 1. Running the docker container of TigerGraph
- Ensure that docker and docker-compose is setup in your machine
- The yaml manifest file that describes the docker setup is in the same directory 
- Run `docker-compose up -d` to have a running instance of TigerGraph DB in the background
    - The docker-compose file does not set up a shared drive between the host and the docker daemon process for running it
    - All configurations will be lost once the docker container is stopped and removed

### 2. Starting the TigerGraph DB
- Once the container is setup, connect to the container `docker exec -it tigergraph /bin/sh`
- Run `su tigergraph` to be in the correct user environment
- Run `gadmin start all` to start TigerGraph DB

### 3. Accessing TigerGraph
- Once started, access `http://localhost:14240` for the TigerGraph Visual IDE

### 4. Loading reference data into TigerGraph (Optional)
- Run `docker cp gsql_ref_examples tigergraph:/home/tigergraph` on the host terminal to copy the folder into the container
- Run `sudo chown -R tigergraph:tigergraph /home/tigergraph/gsql_ref_examples` in the container to set the correct permission
- Run `./graph_install.sh socialNet` in the gsql_ref_examples folder to load the socialNet graph

### 5. Loading foursquare data into TigerGraph
- Unzip the files from the dataset [link](https://archive.org/details/201309_foursquare_dataset_umn) into the 
dataset directory
    - The docker-compose file has bind the dataset directory in the host machine to the container home directory  
- Run `gsql graph_create.gsql` in the home directory of the container to create the graph schema and load the foursquare dataset

## Useful Command
- `gsql` : This will start the GSQL shell inside the docker container
