#SEARCH_FOLDER=./tmp/maven-repository-5.7.7
FOLDER_TO_UPLOAD=$1
NEXUS_URL=http://localhost:8081/repository/maven-releases/
NEXUS_SERVER_ID=nexus-local
rootStrLength=`expr length $FOLDER_TO_UPLOAD + 1` 
 

for f in $(find $FOLDER_TO_UPLOAD -type f); 
do 

	toFile=${f:$rootStrLength};
	echo $toFile
	mvn wagon:upload-single -Dwagon.fromFile=$f -Dwagon.url=$NEXUS_URL -Dwagon.serverId=$NEXUS_SERVER_ID -Dwagon.toFile="$toFile";
done


