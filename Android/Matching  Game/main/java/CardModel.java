package ca.on.sl.comp208.assign1dingram;

/**
 * Created by DGWIngram on 2017-01-23.
 */

public class CardModel {
    private int image;
    private CardState currentState;

    public CardModel(int image, CardState currentState) {
        this.image = image;
        this.currentState = currentState;
    }

    public CardModel() { }

    public CardModel(int image) {
        this.image = image;
        currentState = CardState.FACE_DOWN;
    }

    public CardState getCurrentState() {return currentState;}
    public void setCurrentState(CardState currentState) {this.currentState = currentState;}

    public int getImage() {return image;}
    public void setImage(int image) {this.image = image;}
}
