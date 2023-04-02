import React from "react";
import {Button} from 'antd';
import {LoanApplicationData} from "../types/DataTypes";

interface Props {
    loanApplication: LoanApplicationData
    handleApprove: (loanApplication: LoanApplicationData) => void
    handleReject: (loanApplication: LoanApplicationData) => void
    handleReview: (loanApplication: LoanApplicationData) => void
}

const AdminAction: React.FC<Props> = ({loanApplication, handleApprove, handleReject, handleReview}) => {

    const status = loanApplication.status[0].status

    // Disable buttons based on the status
    const isReviewDisabled = (status === "IN_REVIEW" || status === "REJECTED" || status === "APPROVED" || status === "WITHDRAW");
    const isApproveDisabled = (status === "REJECTED" || status === "APPROVED" || status === "SUBMITTED" || status === "WITHDRAW");
    const isRejectDisabled = (status === "REJECTED" || status === "APPROVED" || status === "SUBMITTED" || status === "WITHDRAW");

    return (
        <div>
            <Button style={isApproveDisabled ? {
                backgroundColor: 'gray',
                borderColor: 'lightgray'
            } : {backgroundColor: 'green', borderColor: 'lightgreen'}} disabled={isApproveDisabled}
                    onClick={() => handleApprove(loanApplication)}>
                Approve
            </Button>
            <Button style={isRejectDisabled ? {backgroundColor: 'gray', borderColor: 'lightgray'} : {
                backgroundColor: 'red',
                borderColor: 'lightred'
            }} disabled={isRejectDisabled} onClick={() => handleReject(loanApplication)}>
                Reject
            </Button>
            <Button style={isReviewDisabled ? {
                backgroundColor: 'gray',
                borderColor: 'lightgray'
            } : {backgroundColor: 'orange', borderColor: 'lightorange'}} disabled={isReviewDisabled}
                    onClick={() => handleReview(loanApplication)}>
                Review
            </Button>
        </div>
    );
}

export default AdminAction