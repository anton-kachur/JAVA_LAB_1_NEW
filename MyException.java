 static class MyException extends Exception{
        private int number;
        private int getNumber(){return number;}

        MyException() {}
        MyException(String message, int num){
            super(message);
            number = num;
        }
    }
