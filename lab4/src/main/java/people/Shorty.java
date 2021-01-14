package people;


import exceptions.CantPokeException;
import general.Size;

public class Shorty extends Human implements WorkableInSeadbeds {
    private Size weight;
    private Face face;

    public void poke(Human human, Object by) throws CantPokeException {
        if (Math.random() < 0.5)
            throw new CantPokeException(CantPokeException.Reason.values()[
                    //выбираем рандомную константу из массива всех констант Enum
                    (int) (Math.random() * CantPokeException.Reason.values().length)
                    ]);
        System.out.println(this + " ткнул " + human + " используя объект " + by);
    }

    public Shorty() {
    }

    public Shorty(String name) {
        super();
        super.setName(name);
    }

    public Shorty setFace(Face face) {
        this.face = face;
        return this;
    }

    public void getFullDescription() {
        String ans = toString();
        if(getWeight()!=null)
            ans+=" " + getWeight() + " шириной";
        System.out.println(ans);
        if(getFace()!=null)
            System.out.println(getFace());
    }

    public Face getFace() {
        return face;
    }

    @Override
    public String toString() {
        return "Коротышка " + super.toString();
    }

    public Size getWeight() {
        return weight;
    }

    public Shorty setWeight(Size weight) {
        this.weight = weight;
        return this;
    }

    public class Face {

        private Size weightEyes;
        private Color colorCheeks;
        private Color colorHair;
        private Size baldHead;

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

        @Override
        public String toString() {
            return "Лицо{" +
                    "ширина глаз=" + weightEyes +
                    ", цвет щёк=" + colorCheeks +
                    ", цвет волос=" + colorHair +
                    ", лысина=" + baldHead +
                    '}';
        }
    }
}
