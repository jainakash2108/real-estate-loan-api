import React from "react";
import {Button} from 'antd';
import {LoanApplicationData} from "../types/DataTypes";

interface Props {
    loanApplication: LoanApplicationData
    handleWithdraw: (loanApplication: LoanApplicationData) => void
}

const UserAction: React.FC<Props> = ({loanApplication, handleWithdraw}) => {

    const status = loanApplication.status[0].status

    const isWithdrawDisabled = (status === "REJECTED" || status === "APPROVED" || status !== "SUBMITTED");

    return (
        <div>
            <Button style={isWithdrawDisabled ? {
                backgroundColor: 'gray',
                borderColor: 'lightgray'
            } : {backgroundColor: 'red', borderColor: 'lightred'}} disabled={isWithdrawDisabled}
                    onClick={() => handleWithdraw(loanApplication)}>
                Cancel
            </Button>
        </div>
    );
}

export default UserAction