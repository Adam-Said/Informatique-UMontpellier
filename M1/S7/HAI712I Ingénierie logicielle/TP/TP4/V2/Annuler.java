package V2;

public class Annuler extends Commande {
    
    private Bidon bidon;

    public Bidon getBidon() {
        return bidon;
    }

    public void setBidon(Bidon bidon) {
        this.bidon = bidon;
    }

    public Annuler(Bidon bidon) {
        this.bidon = bidon;
    }

    @Override
    public void execute() {
        volDeplace = bidon.getCapacite() - bidon.getVolume();
        bidon.remplir();
    }

    @Override
    public void undo() {
        bidon.setVolume(bidon.getVolume() - volDeplace);
    }
    
}
