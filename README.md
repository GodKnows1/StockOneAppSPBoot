# StockOneAppSpring

The Project is divided into two parts one is the React and one is Springboot and is already deployed on https://stockoneapp-react.herokuapp.com/ 

For the localhost: SpringBoot Code will be the same but in the react part you have to change the urls to localhost:8080 (on this spring boot usually get deployed)

Steps (For the localhost):
1. Download he code from react part and this repository.
2. Open the code in the IDEs : eclipse and VSCode (You can choose of your own)
3. Run the react code -> Go to that folder in terminal and run the cmd < npm start >
4. For the Java side just run the project and it will be at localhost:8080
5. after both running go to localhost:3000/login

Bravo !!

## PostMan Screenshots are available in the folder

## For Testing you have to add an extra antmatcher in WebConfigFile ".antMatchers('/**').permitAll()" so that it can bypass the JWT authentication and comment out line 42 in StockOneApplication.java 

React Part: https://github.com/GodKnows1/StockOneAppReact
