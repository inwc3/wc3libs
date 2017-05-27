java -cp antlr-4.5.3-complete.jar org.antlr.v4.Tool -o out -lib lib grammar/*.g4

javac -cp antlr-4.5.3-complete.jar out/*.java

pause