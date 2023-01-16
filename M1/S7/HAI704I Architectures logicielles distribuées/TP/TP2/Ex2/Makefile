all: compile

compile:
	(cd Client ; mvn compile )
	(cd WebService ; mvn compile)

server:
	(cd WebService ; mvn exec:java@HotelPublisher)

client:
	(cd Client ; mvn exec:java@MainAgency)

gui:
	(cd Client ; mvn exec:java@AgencyGUI)

clean:
	find ./Client/target -mindepth 1 ! -regex '^./Client/target/classes\(/.*\)?' -delete
	find ./Client/target/classes -mindepth 1 ! -regex '^./Client/target/classes/META-INF\(/.*\)?' -delete
	find ./WebService/target -mindepth 1 ! -regex '^./WebService/target/classes\(/.*\)?' -delete
	find ./WebService/target/classes -mindepth 1 ! -regex '^./WebService/target/classes/META-INF\(/.*\)?' -delete
