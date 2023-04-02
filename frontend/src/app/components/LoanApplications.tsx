import React from "react";
import {LoanApplicationData, UserInfo} from "../types/DataTypes";
import ApplicationData from "./ApplicationData";

interface Props {
    loanApplications: LoanApplicationData[],
    userInfo: UserInfo
    handleApprove: (loanApplication: LoanApplicationData) => void
    handleReject: (loanApplication: LoanApplicationData) => void
    handleReview: (loanApplication: LoanApplicationData) => void
    handleWithdraw: (loanApplication: LoanApplicationData) => void
}

const LoanApplications: React.FC<Props> = ({
                                               userInfo,
                                               loanApplications,
                                               handleApprove,
                                               handleReject,
                                               handleReview,
                                               handleWithdraw
                                           }) => {

    return (<div>
            {loanApplications && <ApplicationData loanApplications={loanApplications}
                                                  handleApprove={handleApprove}
                                                  handleReject={handleReject}
                                                  handleReview={handleReview}
                                                  handleWithdraw={handleWithdraw}
                                                  userInfo={userInfo}/>}
        </div>
    );
}

export default LoanApplications