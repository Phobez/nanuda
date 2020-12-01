package com.example.nanuda.objects;

/**
 * Object defining which user should pay which user how much in Balances.
 */
public class SummaryListObject {
    private String participantName;
    private String participantSum;

    public SummaryListObject(String participantName, String participantSum) {
        this.participantName = participantName;
        this.participantSum = participantSum;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getParticipantSum() {
        return participantSum;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public void setParticipantSum(String participantSum) {
        this.participantSum = participantSum;
    }
}
