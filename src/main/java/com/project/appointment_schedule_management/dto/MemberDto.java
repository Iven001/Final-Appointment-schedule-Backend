package com.project.appointment_schedule_management.dto;

import lombok.Data;

@Data
public class MemberDto {

    private int memberId;
    private String memberName;

    
    
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    
    
}
