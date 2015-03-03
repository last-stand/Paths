echo 'cleaning..'
rm -f *.class
echo "compiling..."
javac -Xlint:unchecked -cp junit-4.10.jar *.java
if [ $? != 0 ] 
	then exit
fi
echo "running tests.."
java -cp ".;junit-4.10.jar" org.junit.runner.JUnitCore PathsTest
echo $?
echo "_________________________________________________"
java -cp ".;junit-4.10.jar" org.junit.runner.JUnitCore FindPathTest
echo $?