#!bin/sh

average=()
node=()
node+=('s1')
node+=('s2')
node+=('s3')
node+=('m1')
node+=('m2')
node+=('m3')
node+=('l1')
node+=('l2')
node+=('l3')

for k in {0..6}}
do

   for HOSTNAME in s1 s2 s3 m1 m2 m3 l1 l2 l3 
   do
      average+=($(ping -c 10 -i 0.2 -w 6 $HOSTNAME | tail -1| cut -d "/" -s -f5))
   done

    for i in {0..8}
    do
     STR=$STR"${node[$i]}","${average[$i]}"'\n'
    done

  echo -e $STR > /home/ubuntu/RTT
  STR=""
	# echo "${node[$i]}" , "${average[$i]}" > /home/ubuntu/RTT
	#else
        #echo "${node[$i]}" , "${average[$i]}" >> /home/ubuntu/RTT
	#fi
    #done

  sleep 10
done
