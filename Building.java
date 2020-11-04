public static class Building
    {
        protected String name_of_building;
        protected String name_of_locality;
        protected int num_building;

        private Building(String name, String loc_name, int num) {
            name_of_building = name;
            name_of_locality = loc_name;
            num_building = num;
        }

        public void printAll() { System.out.println("Name of building: " + name_of_building + "\nName of locality: " + name_of_locality); }
    }
