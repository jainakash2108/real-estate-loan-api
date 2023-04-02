import React from "react";
import {Progress, Table} from 'antd';
import Action from "./Action";
import {LoanApplicationData, UserInfo} from "../types/DataTypes";

interface Props {
    loanApplications: LoanApplicationData[],
    userInfo: UserInfo
    handleApprove: (loanApplication: LoanApplicationData) => void
    handleReject: (loanApplication: LoanApplicationData) => void
    handleReview: (loanApplication: LoanApplicationData) => void
    handleWithdraw: (loanApplication: LoanApplicationData) => void
}

const ApplicationData: React.FC<Props> = ({
                                              loanApplications,
                                              userInfo,
                                              handleApprove,
                                              handleReject,
                                              handleReview,
                                              handleWithdraw
                                          }) => {

    const getPercent = (status: string | undefined): number => {
        switch (status) {
            case undefined:
                return 0
            case 'APPROVED':
                return 100
            case 'REJECTED':
                return 100
            case 'IN_REVIEW':
                return 50
            default:
                return 10
        }
    }

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Loan Amount',
            dataIndex: 'loanAmount',
            key: 'loanAmount',
        },
        {
            title: 'Equity Amount',
            dataIndex: 'equityAmount',
            key: 'equityAmount',
        },
        {
            title: 'Salary Amount',
            dataIndex: 'salaryAmount',
            key: 'salaryAmount',
        },
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status',
            render: (status: LoanApplicationData['status']) => (
                <Progress
                    percent={getPercent(status[0]?.status)}
                    status={status[0].status === 'REJECTED' ? 'exception' : status[0].status === 'APPROVED' ? 'success' : 'normal'}
                    showInfo={false}
                />
            ),
        },
        {
            title: 'Action',
            key: 'action',
            render: (text: any, record: LoanApplicationData) => (
                <Action key={`${record.id} + ${record.status}`} loanApplication={record}
                        handleReview={handleReview}
                        handleReject={handleReject}
                        handleWithdraw={handleWithdraw}
                        handleApprove={handleApprove}
                        userInfo={userInfo}/>
            ),
        },
    ];

    return <Table dataSource={loanApplications} columns={columns}/>;
}

export default ApplicationData