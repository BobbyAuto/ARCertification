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

# System Overview
The system mainly includes two roles, block builders and verifiers. As shown in Figure \ref{fig:overview}, the role of block builder refers to whenever a student's course score is published by a lecturer, the student information, course information, and final scores will form a new block and be added to the blockchain, and the hash value of the new block will be calculated and stored in the next coming block. Students and interested third-party organizations have the role of verifiers, and the authenticity and integrity of all the academic records of a particular student will be verified by the verification module whenever that student or authorized third-party organization accesses his or her academic records through the system.
 <img width="800" alt="" src="https://github.com/millecodex/COMP842/assets/39792005/34e431d1-4a41-42cc-9828-ea4d6385fd2f">\
 Figure: Each individual ledger is analogous to a block. When one book fills up, a new one begins, carrying over the account balances and thus linking the 'blocks'. 




# Reference
<ol>
  <li>Shivani Pathak, Vimal Gupta, Nitima Malsa, Ankush Ghosh, and R. N. Shaw. Blockchain-
based academic certificate verification system—a review. In Rabindra Nath Shaw, Sanjoy
Das, Vincenzo Piuri, and Monica Bianchini, editors, Advanced Computing and Intelligent
Technologies, pages 527–539, Singapore, 2022. Springer Nature Singapore.</li>
  <li>Jeffrey J Selingo. The future of the degree: how colleges can survive the new credential economy.
Chronicle of Higher Education, 2017.</li>
</ol>
