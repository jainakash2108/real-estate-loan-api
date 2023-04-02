import React from 'react'
import {LoanApplicationData, UserInfo} from '../types/DataTypes'
import {Space} from 'antd';
import AdminAction from './AdminAction';
import UserAction from './UserAction';

interface Props {
    loanApplication: LoanApplicationData,
    userInfo: UserInfo
    handleApprove: (loanApplication: LoanApplicationData) => void
    handleReject: (loanApplication: LoanApplicationData) => void
    handleReview: (loanApplication: LoanApplicationData) => void
    handleWithdraw: (loanApplication: LoanApplicationData) => void
}

const Action: React.FC<Props> = ({
                                     loanApplication,
                                     userInfo,
                                     handleApprove,
                                     handleReject,
                                     handleReview,
                                     handleWithdraw
                                 }) => {

    return (
        <div>
            <Space>
                {userInfo && userInfo.role === "ADMIN" && <AdminAction loanApplication={loanApplication}
                                                                       handleApprove={handleApprove}
                                                                       handleReject={handleReject}
                                                                       handleReview={handleReview}/>}
                {userInfo && userInfo.role === "USER" && <UserAction loanApplication={loanApplication}
                                                                     handleWithdraw={handleWithdraw}/>}
            </Space>
        </div>
    )
}

export default Action
