public static class Locality extends Building
    {
        private String locality_name;
        private String locality_type;

        private Locality(String name, String type, int n) {

            super("Default name", name, n);

            locality_name = name;
            locality_type = type;
        }

        private String getName() { return locality_name; }

        public void printAll() {
            System.out.println("Name of locality: " + locality_name + "\nType of locality: " + locality_type);
        }

        public void printAll(boolean b) {
            if (b)
                System.out.println("Name of locality: " + locality_name + "\nType of locality: " + locality_type);
            else {
                System.out.println("Name of locality: " + locality_name + "\nType of locality: " + locality_type);
                System.out.println("Name of building in locality: " + name_of_building + num_building);
            }
        }
    }
