###Simple commands

a. Print 'CDP JaMP' on the screen
```bash
vitaliilatysh$ echo "CDP JaMP"
CDP JaMP
```
b. Print OS information (name, kernel, etc.)
```bash
vitaliilatysh$ uname -a
Darwin MacBook-Pro.local 18.7.0 Darwin Kernel Version 18.7.0: Tue Aug 20 16:57:14 PDT 2019; root:xnu-4903.271.2~2/RELEASE_X86_64 x86_64
```
c. Print current user
```bash
vitaliilatysh$ whoami
vitaliilatysh
```
d. Print current path
```bash
vitaliilatysh$ pwd
/Users/vitaliilatysh/epam/cdp/Module2/HW3
```
e. Change your password
```bash
vitaliilatysh$ passwd
Changing password for vitaliilatysh.
Old Password:
New Password:
Retype New Password:
MacBook-Pro:HW3 vitaliilatysh$ 
```
f. Print current date
```bash
vitaliilatysh$ date
Sat Sep 14 22:02:54 EEST 2019
```
g. Print current time
```bash
vitaliilatysh$ date +"%T"
22:04:42
```

###Navigation commands

a. Print full path of your home directory
```bash
vitaliilatysh$ echo ~
/Users/vitaliilatysh
```
b. Go to home directory
```bash
vitaliilatysh$ cd ~
```
c. Get all files in the current directory
```bash
vitaliilatysh$ ls -p | grep -v /
Practice9_1_123.txt
Practice9_1_4.txt
Practice9_2_1.txt
Practice9_2_1.txt_extra_info.txt
Practice9_2_2.txt
Practice9_2_3.txt
Quiz.zip
edb_pgagent.app.zip
edb_pgjdbc.app.zip
latysh_uah_4kvartal.rtf
test.html
test.mv.db
test.trace.db
vitalii_latysh_ep_2kv_2019.rtf
vitalii_latysh_esv_2kv_2019.rtf
vitalii_latysh_esv_3kv_2019.rtf
```
d. Create new folder 'test'
```bash
vitaliilatysh$ mkdir test
```
e. Rename 'test' folder to 'newTest'
```bash
vitaliilatysh$ mv test newTest
```
f. Go to 'newTest' folder
```bash
vitaliilatysh$ cd newTest/
```
g. Get current date and save the output to the file 'nowFile'
```bash
MacBook-Pro:newTest vitaliilatysh$ date > nowFile
MacBook-Pro:newTest vitaliilatysh$ ls
nowFile
MacBook-Pro:newTest vitaliilatysh$ cat nowFile 
Sat Sep 14 22:25:34 EEST 2019
```
h. Copy file 'nowFile'. New file name is 'copyOfNowFile'
```bash
vitaliilatysh$ cp nowFile copyOfNowFile
```
i. Get all files information (including date of creation, who created, etc.)
```bash
vitaliilatysh$ ls -l
total 16
-rw-r--r--  1 vitaliilatysh  staff  30 Sep 14 22:31 copyOfNowFile
-rw-r--r--  1 vitaliilatysh  staff  30 Sep 14 22:25 nowFile
```
j. Create empty file 'emptyFile' without text editor
```bash
vitaliilatysh$ touch emptyFile
```
k. Go up(back to home)
```bash
vitaliilatysh$ cd ~
```
l. Delete 'newTest' folder
```bash
vitaliilatysh$ rm -rf newTest/
```
###Permissions
a. Get all files in the current directory
```bash
see step c in the Navigation commands
```
b. Create new folder 'permission'
```bash
vitaliilatysh$ mkdir permission
```
c. Go to this directory
```bash
vitaliilatysh$ cd permission/
```
d. Create new file 'secretFile' with content 'This is very secret info'
```bash
vitaliilatysh$ echo 'This is very secret info' > secretFile
```
e. Change permission of this file so that no one can read it.
```bash
vitaliilatysh$ chmod 333 secretFile
```
f. Get list of files in the current directory with permissions information
```bash
vitaliilatysh$ ls -al
total 8
drwxr-xr-x    3 vitaliilatysh  staff    96 Sep 18 21:43 .
drwxr-xr-x+ 104 vitaliilatysh  staff  3328 Sep 18 21:42 ..
--wx-wx-wx    1 vitaliilatysh  staff    25 Sep 18 21:42 secretFile
```
g. Try to read information from file
```bash
vitaliilatysh$ vim secretFile 
"secretFile" [Permission Denied]
```
###Processes
a. Get list of all running processes
```bash
vitaliilatysh$ ps -A
 PID TTY           TIME CMD
    1 ??        25:47.79 /sbin/launchd
   46 ??         2:01.62 /usr/sbin/syslogd
   47 ??         0:21.88 /usr/libexec/UserEventAgent (System)
...
```
b. Get list of all running processes with CPU and memory usage
```bash
vitaliilatysh$ ps aux
USER               PID  %CPU %MEM      VSZ    RSS   TT  STAT STARTED      TIME COMMAND
vitaliilatysh    31590  24.2  9.9 11780164 826456   ??  U     2Sep19 337:11.29 /Applications/IntelliJ IDEA CE.app/Contents/MacOS/idea
_windowserver      189   7.9  0.4  8578132  33624   ??  Ss   28Aug19 616:14.33 /System/Library/PrivateFrameworks/SkyLight.framework/Resources/WindowServer -daemon
vitaliilatysh     9763   6.5  0.8  6599224  69188   ??  S    30Aug19 140:02.94 /Applications/Skype.app/Contents/Frameworks/Skype Helper.app/Contents/MacOS/Skype Helper --t
_hidd              109   6.0  0.1  4336916   4452   ??  Ss   28Aug19  87:15.21 /usr/libexec/hidd
...
```
c. Get list of all running processes of your user
```bash
vitaliilatysh$ ps aux | grep vitaliilatysh
vitaliilatysh    31590  16.6  9.3 11751572 776408   ??  R     2Sep19 338:02.30 /Applications/IntelliJ IDEA CE.app/Contents/MacOS/idea
vitaliilatysh     9763   5.0  1.0  6599224  86596   ??  S    30Aug19 140:15.16 /Applications/Skype.app/Contents/Frameworks/Skype Helper.app/Contents/MacOS/Skype Helper --t
```
d. Get dynamic real-time view list of processes that are currently being managed by the Linux kernel (CPU, Memory Usage, etc.).
```bash
vitaliilatysh$ top

Processes: 343 total, 2 running, 341 sleeping, 2166 threads                                                                                                                                        22:47:11
Load Avg: 4.75, 3.68, 3.14  CPU usage: 14.92% user, 9.24% sys, 75.82% idle   SharedLibs: 131M resident, 45M data, 14M linkedit. MemRegions: 247001 total, 1943M resident, 35M private, 605M shared.
PhysMem: 8154M used (3261M wired), 38M unused. VM: 1680G vsize, 1372M framework vsize, 421953969(1660) swapins, 427906688(0) swapouts. Networks: packets: 91862965/86G in, 55226898/27G out.
Disks: 35747374/1907G read, 29051585/1876G written.

PID    COMMAND      %CPU TIME     #TH   #WQ  #PORT MEM    PURG   CMPRS  PGRP  PPID  STATE    BOOSTS            %CPU_ME %CPU_OTHRS UID  FAULTS    COW     MSGSENT    MSGRECV    SYSBSD     SYSMACH
99819  Google Chrom 0.0  00:07.46 7     1    77    43M    0B     42M    68927 68927 sleeping *0[4]             0.00000 0.00000    501  171775    1828    10040      6314       138123     21700
99817  Google Chrom 0.0  00:19.96 12    1    152   122M   0B     119M   68927 68927 sleeping *0[6]             0.00000 0.00000    501  514540    2249    98894      54004      110919     308675
...
```
e. Sort previous list by Memory Usage.
```bash
vitaliilatysh$ top -o MEM

Processes: 347 total, 2 running, 345 sleeping, 2217 threads                                                                                                                                        22:49:38
Load Avg: 2.16, 3.01, 2.95  CPU usage: 15.9% user, 9.19% sys, 75.70% idle    SharedLibs: 131M resident, 45M data, 14M linkedit. MemRegions: 247733 total, 2241M resident, 37M private, 609M shared.
PhysMem: 8125M used (3336M wired), 65M unused. VM: 1697G vsize, 1373M framework vsize, 422281665(832) swapins, 428274466(0) swapouts.  Networks: packets: 91863937/86G in, 55227873/27G out.
Disks: 35759029/1908G read, 29060723/1877G written.

PID    COMMAND      %CPU TIME     #TH   #WQ  #PORT MEM    PURG   CMPRS  PGRP  PPID  STATE    BOOSTS            %CPU_ME %CPU_OTHRS UID  FAULTS    COW     MSGSENT    MSGRECV    SYSBSD     SYSMACH
31590  idea         8.8  05:41:11 61    3    2254  3439M  768K   2033M- 31590 1     sleeping *0[14458]         0.00000 0.00000    501  94890538+ 50079   48354388+  12286229+  226175312+ 275871118+
68935  Google Chrom 0.0  03:07:28 11    1    657   1539M- 8688K  307M   68927 68927 sleeping *0[1]             0.00000 0.00000    501  31559825  3463    131219384  55127948   71552584   242791712
88982  Google Chrom 0.0  12:49.12 23    2    952   1313M  0B     1255M  68927 68927 sleeping *0[10]            0.00000 0.00000    501  31551722  9817    1211429+   645209     2560452+   5070247+
0      kernel_task  8.5  10:53:26 162/4 0    0     1158M+ 0B     0B     0     0     running   0[0]             0.00000 0.00000    0    810789+   13      669601066+ 539439696+ 0          0
...
```
###Sort task
a. Generate the file with numbers
```bash
./generator.sh > numbers.txt
```
b. Sorting numerically
```bash
sort -n numbers.txt > sorted.txt
```
c. Time to sorting 5M numbers
1. By java app
```bash
Total time to sort in seconds: 8
```
2. By shell script with:
sort -n ./numbers.txt > ./script_sorted.txt
```bash
vitaliilatysh$ ./sorting.sh 
Total time to sort in seconds: 19
```
sort -n ./numbers.txt
```bash
vitaliilatysh$ ./sorting.sh 
Total time to sort in seconds: 50
```
sort -n ./numbers.txt -o ./script_sorted.txt
```bash
vitaliilatysh$ ./sorting.sh 
Total time to sort in seconds: 2
```
d. Size of generated file
```bash
vitaliilatysh$ ls -lh numbers.txt 
-rw-r--r--  1 vitaliilatysh  staff    27M Sep 18 23:52 numbers.txt
```
e. Run with nohup
```bash
vitaliilatysh$ nohup ./generator.sh > numbers.txt 
```
f. Tail the numbers.txt in the middle of generation
```bash
MacBook-Pro:resources vitaliilatysh$ tail numbers.txt 
28600
29284
8830
3375
14332
7678
19457
8815
7212
10590
934
20108
MacBook-Pro:resources vitaliilatysh$ tail numbers.txt 
17293
26207
25808
23612
11909
27198
13762
25818
6520
10236
1340
22441
```
g. Kill the generation process in the middle of execution
```bash
vitaliilatysh$ kill 93390
```