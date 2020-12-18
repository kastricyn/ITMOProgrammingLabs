package ru.ifmo.se.programming.kastricyn;

public enum ReasonForCreate {
    INTO_POCKET {
        @Override
        public String toString() {
            return "прикарманивания чужих денег";
        }
    },
    SWINDLING_POOR {
        @Override
        public String toString() {
            return "облапошивания бедняков";
        }
    };

}
