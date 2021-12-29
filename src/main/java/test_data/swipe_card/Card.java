package test_data.swipe_card;

public class Card {

    private String cardTitle;
    private String cardText;

    public Card(String cardTitle, String cardText) {
        this.cardTitle = cardTitle;
        this.cardText = cardText;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    @Override
    public String toString() {
        return "Card{" +
               "cardTitle='" + cardTitle + '\'' +
               ", cardText='" + cardText + '\'' +
               '}';
    }
}
