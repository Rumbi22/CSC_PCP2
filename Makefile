#Variables
SRC_DIR := src
OUT_DIR := bin

#Rules
default:
	javac -d bin -sourcepath src src/clubSimulation/*.java

run:
	java -cp $(OUT_DIR) clubSimulation.ClubSimulation


clean:
	rm $(OUT_DIR)/*.class
	rm $(SRC_DIR)/*.CLASS
	rm -rf $(OUT_DIR)