# Illumio_coding_challenge

Approach:

I have made use of hash set to store rules generated from the csv files. Rules from the hash set are compared with the incoming packets to determine their eligibility. Using Hashset should speed up the lookup process. 

The code has 3 class files:

Ilumio_Firewall : This contains main class and methods to split data on basis of port and ip address ranges from the csv as the input. Accept method compares each incoming packet with the rules stored in Hashset and accordinly return a boolean value of true or false.

Input_Data : This has getter and setter methods for variables port, direction, ip address and protocol read from the csv.

Rules : This contain default hashcode and equals method for the purpose of object comparision (Rules and packets)

Due to the time contraint, the code is not optimised and very loosely tested. I did miss adding the validation check for the port range. (Whether port values are between 1 and 65535.) Overall it was a very interesting challenge. 

I am very much interested to be working in the data team as i believe my background aligns with it. Policy team sounds good too!
