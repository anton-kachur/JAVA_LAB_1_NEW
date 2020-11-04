private static class Road
    {
        private String road_type;
        private int number_of_lanes;

        private Road(String type, int num) {
            road_type = type;
            number_of_lanes = num;
        }

        private void printAll() { System.out.println("Road type: " + road_type + "\nNumber of lanes: " + number_of_lanes); }
    }
