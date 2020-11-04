interface SetOfMapMethods
    {
        void setLocalities(int n, boolean b) throws MyException;
        void setRoads(int n, boolean b) throws MyException;
        void setReservoirs(int n, boolean b) throws MyException;
        void setBuildings(int n, boolean b) throws MyException;
        void setTrees(int n, boolean b) throws MyException;
        void setRelief(int n, boolean b) throws MyException;
        void setScale(Scale s, int n);
    }
