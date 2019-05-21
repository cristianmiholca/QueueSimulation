package PT2019.Assignment2.HW2;

/**
 * Clasa descrie un client pentru care se retin: id-ul, timpul de sosire, timpul de servire si timpul in care pleaca.
 */

public class Client {
    private int clientId;
    private int arrivalTime;
    private int serviceTime;
    private int finishTime;

    public Client() {
        this.clientId = 0;
        this.arrivalTime = 0;
        this.serviceTime = 0;
    }

    public Client(int clientId, int arrivalTime, int serviceTime) {
        this.clientId = clientId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                ", finishTime=" + finishTime +
                '}';
    }
}

