<h1 align='center'>Academic Records Certification System Based on Blockchain</h1>
</br></br>

# Programming Languages Used for This System.
### Back-end Development
<ul>
  <li>Java Servlet</li>
</ul>

### Front-end Development
<ul>
  <li>HTML</li>
  <li>CSS</li>
  <li>JavaScript, jQuery</li>
</ul>

### Database
<ul>
  <li>MySQL</li>
</ul>

### Algorithms Used
<ul>
  <li>Asymmetric Encryption algorithm: RSA</li>
  <li>Cryptographic Hash Function: SHA-256</li>
</ul>
<br /><br /><br />

# Introduction
Academic records have always been very important for people in today's modern life because they can indicate people’s knowledge, competence, skill, expertise, and even academic character whether a person has put in an effort to study [1]. Academic records can be used to apply for scholarships for further study and job employment. It is precisely because of the importance of academic records that many people attempt to tamper or falsify their academic records. Fraudulent academic records have long been a serious concern, especially in high-level education and the extant method is either difficult to identify the authenticity of an academic record or long time taking to complete the verification. Although some institutions have started to use digital transcripts online, these online transcripts are generally designed to be used only one time. They are almost the equivalent of paper-based transcripts that can be easily copied, and naturally, the records are not very secure [2]. Thus, a security mechanism for storing documents to ensure the information is original, integrity, authenticity, and easily verified has become a critical need in computer science. In this study, a blockchain-based approach was proposed to store academic records to achieve the authenticity and tamper resistance of academic records, which also enables easy verification from authorized third-party organizations.

The proposed approach is that, first of all, every lecturer will be in the role of block builder, and as a score of the student's subject is published a new block will be built by calculating and storing a hash value of the previous block and the signature of the lecturer, secondly, students and authorized third-party organizations will be in the role of verifier, and all the hash values and signatures of related blocks will be verified to ensure the integrity and authenticity of the historical data when they access the student's academic records. Furthermore, the data recovering mechanism in some extreme situations which result in data tampering or loss, will also be proposed and discussed in detail in the later section.

# Solution Proposal
### Aim and Objectives
To address the problems, this research proposes to design an electronic system based on blockchain technology, which will achieve the below down major objectives:
<ol>
  <li>Design a highly secure data structure to store students' academic records to achieve a trackable, trustworthy, and tamper-resistant system.</li>
  <li>Students can be the owners of their academic records, which means they can authorize access rights to their academic records to any interested third-party organizations.</li>
  <li>When authorized third-party organizations access a student's academic records, the authenticity and integrity of all academic records of that student can be verified with high certainty in real time.</li>
</ol>

### Limitations
<ol>
  <li>In this study, academic records refer to students' transcripts, marks of subjects, and graduation certificates. Other data, such as course enrollment records and faculty members' information, are not within the scope.</li>
  <li>A scenario in which the academic records might be revoked by the educational institution due to the reasons, such as violation of national laws and pregiarism, will not be included in the scope in this study, but could be included in the subsequent study after this project.</li>
  <li>Regarding the technical development of the system, relevant data such as student information and course information will be preliminarily prepared and stored in the Mysql database.</li>
  <li>The programming language Javascript and framework jQuery will be used for the front-end development, and the Java servlet will be used for the back-end development to build blockchain and verify the signature and data authenticity and integrity.</li>
</ol>

## System Overview
The system mainly includes two roles, block builders and verifiers. As shown in Figure 1, the role of block builder refers to whenever a student's course score is published by a lecturer, the student information, course information, and final scores will form a new block and be added to the blockchain, and the hash value of the new block will be calculated and stored in the next coming block. Students and interested third-party organizations have the role of verifiers, and the authenticity and integrity of all the academic records of a particular student will be verified by the verification module whenever that student or authorized third-party organization accesses his or her academic records through the system.
 <img aligh='center' width="800" alt="" src="https://github.com/WeichunAuto/ARCertification/blob/main/WebContent/images/System%20Overview.png">
 <p align='center'>Figure 1: System Overview. </p>
 
When a verifier accesses a student's academic record, the verification module will return the status of the verification result. Once it is verified that any score record has been tampered with, the data recovery module, which is shown in Figure 1, will be automatically triggered, and a successful status of verification result will be returned if the tampered record was recovered, otherwise a failure status of verification result will be returned.

## Consensus Mechanism
Consensus refers to the agreed rules that the majority of a group has agreed in favor of a decision. Consensus mechanism plays a vital role in maintaining the security and efficiency of blockchain. All nodes in the blockchain have equal status and achieve consensus by using the prior agreement of the rules and following the principle of majority dominance [3]. In this research, the consensus mechanism refers to the legitimate lecturer whose signature was successfully verified by the system when publishing a student's score and obtains the building rights of a new block. The detailed process follows below steps:
<ol>
  <li>The legitimate lecturer of a certain course is ready to publish a student's score.</li>
  <li>The legitimate lecturer gives a signature for the course information, student information, and a score of the course using the private key, and then submits it to the verification module of the system.</li>
  <li>The verification module verifies the signature of the lecturer with the public key. If the signature verification is successful, then the lecturer obtains building a new block right.</li>
</ol>

## The Process of Building Blocks
The extensive use of cryptography is a vital characteristic of blockchain. In a blockchain, the blocks are “chained” via hash values, and the hash value of each block is stored in the next block header. This connection with the hash value of blocks forms a chain of blocks [4]. Similarly, the basic design of this research follows the standard structure of blockchain. Other improvements applicable to this research are described as follows:
<ul>
  <li><b>Latest Version. </b>In this research, a version value was maintained in the SQL database for blocks of each student to avoid scanning the whole blockchain when querying a student's academic records. In other words, as shown in Figure 2, before building a new block, first of all, the system retrieves the latest version number of the block related to the student from the SQL database, secondly, the system finds the block corresponding to the student's latest version number from the blockchain. Finally, the latest version number automatically increases by 1, building a new block, and updating the increased latest version number to the new block and SQL database.<br />
    
  <img aligh='center' width="800" alt="" src="https://github.com/WeichunAuto/ARCertification/blob/main/WebContent/images/chain.png">
   <p align='center'>Figure 2: The Structure of Blockchain. </p>
  
  </li>
  <li><b>Copy Information from Previous Block. </b>When the latest block(with the latest version number) of the student was retrieved, In addition to the latest version number being automatically increased by 1, the data information of this block will be simultaneously copied to the newly built block. As illustrated in Figure 2, for student-1, the data of student-1's third block includes the data of course 1 and course 2. It is worth noting that, after the latest block(with the latest version number) of the student is retrieved, the latest block will be verified by the verification module first. The purpose is to ensure the authenticity and integrity of the data in the latest block before it is copied to the newly built block. The mechanism of the verification module will be described in the later section.</li>
  <li><b>Append Empty Block. </b>After each new block is built and added to the blockchain, an empty block is also created and appended to the end of the blockchain. The purpose of this is to store the hash value of the last new block in the empty block to enhance the security of the blockchain data structure. The details will be discussed in the later section as well.</li>
  <li><b>Course Data Container. </b>Different from the Merkle tree in Bitcoin [5], all courses and corresponding scores are stored in an ordered container, and the hash value of the container is saved in the block header.</li>
</ul>

The block header in this research mainly includes below fields:
<ul>
  <li>
    Version - Corresponding to the latest version number which was stored in the SQL database, each block header has a version field, that records the version number of the student block.
  </li>
  <li>
    Hash - The hash value of the container which orderly stores course data, as shown in above Figure 3, the course data records the student ID, course ID, and the related score of the student.<br/><br />
    <img aligh='center' width="800" alt="" src="https://github.com/WeichunAuto/ARCertification/blob/main/WebContent/images/Course%20Data.png">
   <p align='center'>Figure 3: The Data Body of Course. </p>
  </li>
  <li>Previous hash - The hash value of the previous block header.</li>
  <li>Timestamp - The timestamp a new block was built.</li>
  <li>Student ID - It's a unique ID number, that represents a unique student.</li>
  <li>Lecturer ID - It's a unique ID number, that represents a unique lecturer.</li>
  <li>Signature - The signature for a message, when a legitimate lecturer in a certain discipline publishes a student's score.</li>
  <li>Message - The message which was shown in Figure 4, signed by a legitimate lecturer, when publishes a student's score.<br/><br />
    <img aligh='center' width="800" alt="" src="https://github.com/WeichunAuto/ARCertification/blob/main/WebContent/images/message.png">
   <p align='center'>Figure 4: The Message Body. </p>
  
  </li>
</ul>

## The Mechanism of Verification Module
Students and interested third-party organizations have the role of verifiers. When a verifier accesses a student's academic records, for student-1, the system will first find all blocks of the student and the corresponding next block in the blockchain based on the latest version number which is stored in the SQL database and student ID of student-1. The verification flow was shown in Figure 5 as below steps:<br/><br />
<img aligh='center' width="800" alt="" src="https://github.com/WeichunAuto/ARCertification/blob/main/WebContent/images/verification%20flow.png">
   <p align='center'>Figure 5: The Verification Flow. </p>
<ol>
  <li>
    Compare and validate each of the hash values recalculated for the container that orderly stores course data and each of the hash values stored in the block header, shown in Figure 5.
  </li>
  <li>
    If the two corresponding hash values in the above second step are equal, then compare and validate each of the recalculated hash values of the block header and each of the previous hash values stored in the next block header, shown in Figure 5.
  </li>
  <li>
    If the two corresponding hash values in the above third step are equal as well, then validate the signature with the lecture's public key. 
  </li>
  <li>
    If the signature verification is successfully passed, then last compare if the student ID and score involved in the message, shown in Figure 4, are equal to the student ID and score stored in the course data, shown in Figure 3.
  </li>
</ol>

Only and only if the above four steps are all successfully passed the verification in all related blocks of student-1, then it indicates that the academic records of student-1 are authentic and integrity.

## The Mechanism of Data Recovery Module
During the verification stage, if any of the student’s intermediate block data is tampered with which results in verification failure, such as the score of the corresponding course, then the process will automatically go into the data recovery module, shown in Figure 5. In the data recovery module, assuming the version value, in the tampered block, is $n$, the flow of data recovery follows as below , shown in Figure 6. Note that, If the tampered course data has no copies in other blocks, it cannot be recovered.<br/><br />
<img aligh='center' width="800" alt="" src="https://github.com/WeichunAuto/ARCertification/blob/main/WebContent/images/recovery%20flow.png">
   <p align='center'>Figure 6: The Data Recovery Flow. </p>
<ol>
  <li>
    The data recovery module first tries to find a trusted block (has not been tampered with) related to the same student, and the version value of the trusted block is $n+1$.
  </li>
  <li>
    If the trusted block in above Step 1 wasn't found, then try to find another trusted block in which the version value is $n-1$.
  </li>
  <li>
    Compare each course data in the trusted block with each course data in the tampered block, if a different course data is found then copy it from the trusted block and replace it in the tampered block.
  </li>
  <li>
    If Step 3 is successful, then go to the verification module again to ensure the tampered block was successfully recovered. 
  </li>
</ol>

## Student Authorization Process
Student, as a verifier, holds ownership of their academic records. Whenever they need a higher education or go for a job employment, they can authorize access rights to any interested organizations through an access code with a fixed valid period. Interested organizations can access the student's academic records at any time before the access code expires, and the academic records will be automatically verified by the verification module of the system in each access.

# Conclusion and Future Works
Using blockchain technology, the paper introduces a secure data structure to store students' academic records and achieve a trustworthy, and tamper-resistant system. The latest version mechanism improves the query efficiency of blocks, avoiding scanning the entire blocks. The lecturers' signature data stored in block headers enhances the security of the proposed data structure. Finally, the data recovery module can recover the tampered block data to a certain extent, which further ensures the security of the data structure.<br />

In the future, based on the proposed data structure, a decentralized academic certification platform, that allows different educational institutions to join in, and achieve an efficient, time-saving, trackable, trustworthy, and tamper-resistant mechanism for all needed students, is very worth exploring.

# Reference
<ol>
  <li>Shivani Pathak, Vimal Gupta, Nitima Malsa, Ankush Ghosh, and R. N. Shaw. Blockchain-
based academic certificate verification system—a review. In Rabindra Nath Shaw, Sanjoy
Das, Vincenzo Piuri, and Monica Bianchini, editors, Advanced Computing and Intelligent
Technologies, pages 527–539, Singapore, 2022. Springer Nature Singapore.</li>
  <li>Jeffrey J Selingo. The future of the degree: how colleges can survive the new credential economy.
Chronicle of Higher Education, 2017.</li>
  <li>Du Mingxiao, Ma Xiaofeng, Zhang Zhe, Wang Xiangwei, and Chen Qijun. A review on
consensus algorithm of blockchain. In 2017 IEEE International Conference on Systems, Man,
and Cybernetics (SMC), pages 2567–2572, 2017.</li>
  <li>Konstantinos Christidis and Michael Devetsikiotis. Blockchains and smart contracts for the
internet of things. Ieee Access, 4:2292–2303, 2016.</li>
  <li>Satoshi Nakamoto. Bitcoin: A Peer-to-Peer Electronic Cash System. DecentralizedBusiness-
Review. 2008.</li>
</ol>
