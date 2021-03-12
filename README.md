# Huffman Coding 

[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](http://ansicolortags.readthedocs.io/?badge=latest) [![GitHub license](https://img.shields.io/github/license/Naereen/StrapDown.js.svg)](https://github.com/matthewcheng222/ECM1414_CA/blob/main/LICENSE)

This Huffman Coding program is developed for the continous assessment of ECM1414 Data Structures and Algorithms

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Prerequisites

This project is written in Java SE-11 programming language. In order to run the program, you will need a working Java Development Kit on your local machine (Available [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)). You will also need a text-editor or an Integrated development environment (IDE) if you wish to develop further on this project.

### Installing

Clone this repository on your local computer using [this link](https://github.com/matthewcheng222/ECM1414_CA.git) or by any other means.

### Running the Program

Confirm that you see a folder named ```ECM1414_CA```, inside of this folder you will be able to see subfolders ```src``` and ```resources```.

First, populate the folder ```resources/ filesToTest``` with files that you would like to compress. Note that only .txt files are supported. 

In the terminal, contirm that your current directory is set to ```src```. Compile the code by typing ```javac HuffmanCoding.java``` and press enter, a class file should then be generated inside the same folder.

Run the program by typing ```java HuffmanCoding```, this will prompt you a menu where you could select different options depending on your needs.

For example, entering ```compress fileToCompress.txt compressedFile.bin``` the compressing algorithm will take file ```fileToCompress.txt``` and compress it.

The output file ```compressedFile.bin``` and ```comopressedFile.bin.huffmancode``` will then be generated and saved in the ```out``` folder.

If you would like to decompress ```compressedFile.bin```, run the program again and type ```decompress compressedFile.bin output.txt```. 

The decompressing algorithm will then decompress this file and save the output in ```output.txt``` also in the ```out``` folder.

Note: large datasets are not included since they are too large for file uploads. They can be obtained from [repetitive corpus](http://pizzachili.dcc.uchile.cl/repcorpus.html)

### List of books and datasets used for testing

[book_Alice's_Adventures_In_Wonderland(ENG).txt](https://www.gutenberg.org/files/11/11-0.txt)

[book_Alice's_Adventures_In_Wonderland(FR).txt](https://www.gutenberg.org/files/55456/55456-0.txt)

[book_Alice's_Adventures_In_Wonderland(PT).txt](http://www.gutenberg.org/files/17482/17482-0.txt)

[book_Oliver_Twist(ENG).txt](https://www.gutenberg.org/files/730/730-0.txt)

[book_Oliver_Twist(FR).txt](https://www.gutenberg.org/files/61994/61994-0.txt)

[book_Oliver_Twist(PT).txt](https://www.gutenberg.org/files/61994/61994-0.txt)

[dataset_artifical(rs.13).txt](http://pizzachili.dcc.uchile.cl/repcorpus/artificial/rs.13.7z)

[dataset_artificial(fib41).txt](http://pizzachili.dcc.uchile.cl/repcorpus/artificial/fib41.7z)

[dataset_pseudo_real(dsources.001.2).txt](http://pizzachili.dcc.uchile.cl/repcorpus/pseudo-real/sources.001.2.7z)

[dataset_real(world_leaders).txt](http://pizzachili.dcc.uchile.cl/repcorpus/real/world_leaders.7z)

***

## Versioning

[SemVer](http://semver.org/) is used for versioning

## Authors

* **Matthew Cheng** - *Initial work* - [matthewcheng222](https://github.com/matthewcheng222)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
