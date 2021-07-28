# codefellowship

## Description :
appliction simply allows to each user to sign up and has his own profile , besides the ability to create posts , and see other people posts :

## Dependencies :
you need to install Gson library to use run the code (gradle run)


## Instructions :
* run the application :

                gradle run

* run tests :

                ./gradlew test


Example :

                //this to get a quote from the quotesFile
                FileOperations fileOperations = new FileOperations() ;
                String filePath = "app/src/main/resources/quotesFile.json" ;
                System.out.println(fileOperations.getQuote(filePath)); //this will print out a random quote from the file

                //this to get the quote from the api
                HttpOperations httpOperations = new HttpOperations("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en" , "GET");
                if(httpOperations.startConnection().equals("success")) {
                Gson gsonObj = new Gson();
                ApiQuote reqQuote = gsonObj.fromJson(httpOperations.getData(), ApiQuote.class);
                fileOperations.insertDataInFile(reqQuote.getQuoteAuthor() , reqQuote.getQuoteText() , "app/src/main/resources/quotesFile.json");
                System.out.println("The author name : "+reqQuote.getQuoteAuthor());
                System.out.println("The quote : "+reqQuote.getQuoteText());
                }




