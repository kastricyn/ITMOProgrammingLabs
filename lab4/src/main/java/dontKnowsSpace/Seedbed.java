package dontKnowsSpace.place;

import dontKnowsSpace.exсeptions.CultivatedException;

import java.util.Objects;

public class Seedbed extends GrowPlace {
    private boolean cultivated;

    public Seedbed() {
    }

    public Seedbed(String name) {
        super(name);
    }
    public Seedbed(String name, GrowType[] types) {
        super(name, types);
    }

    public Seedbed cultivate() throws CultivatedException {
        System.out.println(this.getName() + "> попытка обработки");
        if (cultivated)
            throw new CultivatedException();
        cultivated = true;
        System.out.println(this.getName() + "> обработана");
        return this;
    }

    public boolean cultivateable(){
        return !cultivated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seedbed)) return false;
        if (!super.equals(o)) return false;
        Seedbed seedbed = (Seedbed) o;
        return cultivated == seedbed.cultivated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cultivated);
    }
}
