IMDb Technical Assignment

Overview

This project implements a standalone RESTful API application using Java and Spring Boot based on the IMDb public datasets.

The application imports IMDb TSV datasets during startup, builds optimized in-memory indexes, and exposes APIs for querying movie, actor, director, writer, and rating information.

The main focus of the implementation is:

- Performance efficiency
- Memory-conscious design
- Clean architecture
- Fast query execution using precomputed indexes
- Streaming  large datasets

---

Tech Stack

- Java 17
- Spring Boot
- Maven
- JUnit 5

No external database is required.
All data is loaded and indexed in-memory during application startup.

---

Dataset Source

IMDb datasets:

https://www.imdb.com/interfaces

Required files:

- title.basics.tsv.gz
- title.crew.tsv.gz
- title.principals.tsv.gz
- title.ratings.tsv.gz
- name.basics.tsv.gz


notice:
Dataset must be download and place  in following directory /resources/dataset
---

Architecture



The datasets are processed line-by-line using "BufferedReader" and "GZIPInputStream" to avoid loading entire files into memory.

---

In-Memory Indexes

The  indexes are built during startup

These indexes allow fast runtime queries without scanning datasets repeatedly.

---

API Endpoints

1. Titles where director and writer are the same alive person

GET /api/v1/same-alive-director-writer

---

2. Common titles between two actors

GET /api/v1/titles/common?actor1={id}&actor2={id}

Example:

GET /api/v1/titles/common?actor1=nm0000093&actor2=nm0000138

---

3. Best titles per year for a genre

GET /api/v1/genre/{genre}/best-titles

Example:

GET /api/v1/genre/Drama/best-titles

---

4. HTTP request counter

GET /api/v1/metrics/requests

Returns the number of HTTP requests received since application startup.

---


Running the Application

1. Clone project

git clone <repository-url>

---

2. Place IMDb datasets

Put all ".tsv.gz" files inside:

dataset/

Example:

dataset/title.basics.tsv.gz

---

3.  Then Run application

---

Design Decisions

Why no database?

The IMDb dataset is mostly static and read-heavy for this assignment.

Using in-memory indexes provides:

- faster lookups
- simpler deployment
- standalone execution
- reduced query complexity

---

Why streaming ingestion?

The IMDb datasets are several gigabytes in size.

The application processes files line-by-line to avoid excessive memory usage.


Person Life Status

The IMDb dataset does not explicitly specify whether a person is alive.

The application uses the following logic:

- deathYear != null → DEAD
- deathYear == null && birthYear != null → ALIVE
- birthYear == null && deathYear == null → UNKNOWN

Only persons with ALIVE status are included in the "same director and writer" endpoint.


---

Possible Future Improvements

Given more time, the following improvements could be implemented:


- Writing Complete Integration and Unit tests.
- Pagination support in All services
- implement LRU cache to speed up  api response
- Custom high-performance TSV parser
- Memory optimization

---

Notes

This implementation intentionally focuses on:

- correctness
- performance
- clean architecture


be carefule to increase java heap at run time if it is needed.

I limited some result because I have  not enough Ram capacity on my old system. 
