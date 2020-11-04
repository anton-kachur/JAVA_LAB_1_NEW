public enum Scale {
        SCALE_X1 {
            public String toString() {
                return "Default";
            }
        },
        SCALE_X2 {
            public String toString() {
                return "x2";
            }
        },
        SCALE_X3 {
            public String toString() {
                return "x3";
            }
        },
        SCALE_X5 {
            public String toString() {
                return "x5";
            }
        },
        SCALE_X10 {
            public String toString() {
                return "x10";
            }
        },
        SCALE_X100 {
            public String toString() { return "x100"; }
        };

        public String setTimeZone(int n) {
            return (n>0)? ("Timezone: +" + n): ("Timezone: "+n);
        }
    }
