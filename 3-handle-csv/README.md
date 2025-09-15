# 3-handle-csv

### Keywords 📋

CSV, Large dataset, Dockerfile, JVM, Memory Heap, Optimization, Gradle

### What is this about? 🤔

This project is a console application, you can run it directly on the command line or using a docker container (see the Dockerfile inside it).

This code is related to generation and memory optimization of a big CSV file with 20mi lines of data.

The CSV file will be created using random data and with 4 fields. There are two approaches to generate it: using chunks of data or the full list with all lines. I leave the second one commented by default because it needs a lot of memory heap to work and the code implementation is there to show the difference about how we can optimize this.

Run it directly as a console application or by docker container will create a `output/csv_file.csv` file on the relative path.

### Run using Docker 🐳

```bash
# On root project folder
docker build -t <any_image_name> .

# Run the application and expose the file using volume
docker run -t -v $PWD/output:/app/output <any_image_name>
```

### System configuration ⚙️

- Java OpenJDK Runtime Environment Corretto-21.0.8.9.1
- IntelliJ IDEA 2025.2.1 (Community Edition)
- Gradle 9.0.0
- OS Linux Mint 22.1 Cinnamon
- Docker 28.4.0