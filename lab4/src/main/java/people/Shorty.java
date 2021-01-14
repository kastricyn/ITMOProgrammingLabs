package people;


import exceptions.CantPokeException;
import general.Color;
import general.Size;

import java.util.Objects;

public class Shorty extends Human implements WorkableInSeadbeds {
    private Face face;
    private Size weight;

    public Shorty() {
    }

    public Shorty(String name) {
        super();
        super.setName(name);
    }


    public void poke(Human human, Object by) throws CantPokeException {
        if (Math.random() < 0.5)
            throw new CantPokeException(CantPokeException.Reason.values()[
                    //выбираем рандомную константу из массива всех констант Enum
                    (int) (Math.random() * CantPokeException.Reason.values().length)
                    ]);
        System.out.println(this.getName() + " ткнул " + human.getName() + " используя объект " + by);
    }

    public void printFullDescription() {
        String ans = toString();
        if (getWeight() != null)
            ans += " " + getWeight() + " шириной";
        System.out.println(ans);
        if (getFace() != null)
            System.out.println(getFace());
    }


    public Face getFace() {
        return face;
    }

    public Size getWeight() {
        return weight;
    }


    public Shorty setFace(Face face) {
        this.face = face;
        return this;
    }

    public Shorty setWeight(Size weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return "Коротышка " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shorty)) return false;
        if (!super.equals(o)) return false;
        Shorty shorty = (Shorty) o;
        return Objects.equals(getFace(), shorty.getFace()) && getWeight() == shorty.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFace(), getWeight());
    }

    public class Face {

        private Size weightEyes;
        private Color colorCheeks;
        private Color colorHair;
        private Size baldHead;
        private Size eyebrows;

        public Size getWeightEyes() {
            return weightEyes;
        }

        public Color getColorCheeks() {
            return colorCheeks;
        }

        public Color getColorHair() {
            return colorHair;
        }

        public Size getBaldHead() {
            return baldHead;
        }

        public Size getEyebrows() {
            return eyebrows;
        }

        public Face setWeightEyes(Size weightEyes) {
            this.weightEyes = weightEyes;
            return this;
        }

        public Face setColorCheeks(Color colorCheeks) {
            this.colorCheeks = colorCheeks;
            return this;
        }

        public Face setColorHair(Color colorHair) {
            this.colorHair = colorHair;
            return this;
        }

        public Face setBaldHead(Size baldHead) {
            this.baldHead = baldHead;
            return this;
        }

        public Face setEyebrows(Size eyebrows) {
            this.eyebrows = eyebrows;
            return this;
        }

        @Override
        public String toString() {
            return "Лицо{" +
                    "ширина глаз=" + weightEyes +
                    ", цвет щёк=" + colorCheeks +
                    ", цвет волос=" + colorHair +
                    ", лысина=" + baldHead +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Face)) return false;
            Face face = (Face) o;
            return getWeightEyes() == face.getWeightEyes() && getColorCheeks() == face.getColorCheeks() && getColorHair() == face.getColorHair() && getBaldHead() == face.getBaldHead() && getEyebrows() == face.getEyebrows();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getWeightEyes(), getColorCheeks(), getColorHair(), getBaldHead(), getEyebrows());
        }
    }
}
