#SEARCH_FOLDER=./tm/maven-repository-5.7.7
SEARCH_FOLDER=$1
baseStringLength=`expr length $SEARCH_FOLDER + 1` 
 

for f in $(find $SEARCH_FOLDER -type f); 
do 

	toFile=${f:$baseStringLength};
	echo $toFile
	mvn wagon:upload-single -Dwagon.fromFile=$f -Dwagon.url="http://localhost:8081/repository/maven-releases/" -Dwagon.serverId="nexus-local" -Dwagon.toFile="$toFile";
done


