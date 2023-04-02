import React, {useEffect, useState} from "react";
import LoanApplications from "../components/LoanApplications";
import {Col, Row} from 'antd';
import AdminRegisterButton from "../components/AdminRegisterButton";
import LogoutButton from "../components/LogoutButton";
import {
    approveLoanApplication,
    fetchLoanApplications,
    fetchLoggedInUserDetails,
    rejectLoanApplication,
    startReview,
    withdrawLoanApplication
} from "../apis/api";
import {LoanApplicationData, UserInfo} from "../types/DataTypes";
import ErrorBanner from "../components/ErrorBanner";

const AdviserViewPage: React.FC = () => {

    const [loanApplications, setLoanApplications] = useState<LoanApplicationData[]>([])
    const [userInfo, setUserInfo] = useState<UserInfo>()
    const [error, setError] = useState<string>('')
    const [refetch, setRefetch] = useState<boolean>(false)

    const handleApprove = (loanApplication: LoanApplicationData) => {
        approveLoanApplication(loanApplication.id)
            .then(response => {
                setRefetch((currentValue) => !currentValue)
                setError('')
            })
            .catch(error => {
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    }

    const handleReject = (loanApplication: LoanApplicationData) => {
        rejectLoanApplication(loanApplication.id)
            .then(response => {
                setRefetch((currentValue) => !currentValue)
                setError('')
            })
            .catch(error => {
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    }

    const handleReview = (loanApplication: LoanApplicationData) => {
        startReview(loanApplication.id)
            .then(response => {
                setRefetch((currentValue) => !currentValue)
                setError('')
            })
            .catch(error => {
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    }

    const handleWithdraw = (loanApplication: LoanApplicationData) => {
        withdrawLoanApplication(loanApplication.id)
            .then(response => {
                setRefetch((currentValue) => !currentValue)
                setError('')
            })
            .catch(error => {
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    }

    useEffect(() => {
        console.log("fetchLoanApplications :: useEffect :: LoanApplications")
        fetchLoanApplications()
            .then(response => {
                setLoanApplications(response)
                setError('')
            })
            .catch(error => {
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    }, [refetch])

    useEffect(() => {
        console.log("fetchLoggedInUserDetails :: useEffect :: AdviserViewPage")
        fetchLoggedInUserDetails()
            .then(response => {
                setUserInfo(response)
                setError('')
            })
            .catch(error => {
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    }, [])

    return (
        <div>
            <main>
                <Row justify="center" style={{marginTop: '5rem'}}>
                    {error !== '' && <ErrorBanner message={error}/>}
                </Row>
                <Row justify="center" style={{marginTop: '5rem'}}>
                    <Col span={5}>
                        <Row justify="center" style={{marginTop: '1rem'}}>
                            <h2>Welcome, {userInfo && userInfo.name}</h2>
                        </Row>
                        <Row justify="center" style={{marginTop: '2rem'}}>
                            <LogoutButton/>
                        </Row>
                        <Row justify="center" style={{marginTop: '3rem'}}>
                            <AdminRegisterButton/>
                        </Row>
                    </Col>
                    <Col span={14}>
                        {loanApplications && userInfo && <LoanApplications userInfo={userInfo}
                                                                           loanApplications={loanApplications}
                                                                           handleApprove={handleApprove}
                                                                           handleReject={handleReject}
                                                                           handleReview={handleReview}
                                                                           handleWithdraw={handleWithdraw}/>}
                    </Col>
                </Row>
            </main>
        </div>
    );
}

export default AdviserViewPage