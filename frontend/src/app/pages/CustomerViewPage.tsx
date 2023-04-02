import React, {useEffect, useState} from "react";
import LoanApplicationForm from "../components/LoanApplicationForm";
import LoanApplications from "../components/LoanApplications";
import {Col, Row} from 'antd';
import LogoutButton from "../components/LogoutButton";
import {
    approveLoanApplication,
    fetchLoanApplications,
    fetchLoggedInUserDetails,
    rejectLoanApplication,
    startReview,
    submitLoanApplication,
    withdrawLoanApplication
} from "../apis/api";
import {LoanApplicationData, LoanRequest, UserInfo} from "../types/DataTypes";
import SuccessBanner from "../components/SuccessBanner";
import ErrorBanner from "../components/ErrorBanner";

const CustomerViewPage: React.FC = () => {

    const [loanApplications, setLoanApplications] = useState<LoanApplicationData[]>([])
    const [userInfo, setUserInfo] = useState<UserInfo>()
    const [loanRequest, setLoanRequest] = useState<LoanRequest>()
    const [error, setError] = useState<string>('')
    const [success, setSuccess] = useState<string>('')
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

    const handleSubmit = (loanRequest: LoanRequest) => {
        console.log("LoanApplicationForm :: submitLoanApplication :: handleSubmit")
        submitLoanApplication(loanRequest)
            .then(response => {
                setLoanRequest({
                    loanAmount: '0',
                    equityAmount: '0',
                    salaryAmount: '0',
                })
                setSuccess("Loan application has been submitted successfully")
                setError('')
                setRefetch((currentValue) => !currentValue)
            })
            .catch(error => {
                setSuccess('')
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    };

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
        console.log("fetchLoggedInUserDetails :: useEffect :: CustomerViewPage")
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
                    {success !== '' && <SuccessBanner message={success}/>}
                </Row>
                <Row justify="center" style={{marginTop: '5rem'}}>
                    <Col>
                        <Row justify="center" style={{marginTop: '1rem'}}>
                            <Col span={10}>
                                <h2>Welcome, {userInfo && userInfo.name}</h2>
                            </Col>
                        </Row>
                        <Row justify="center" style={{marginTop: '3rem'}}>
                            <Col span={10}>
                                <LogoutButton/>
                            </Col>
                        </Row>
                    </Col>
                    <Col span={1}/>
                    <Col span={5}>
                        <LoanApplicationForm handleSubmit={handleSubmit}/>
                    </Col>
                    <Col span={1}/>
                    <Col span={12}>
                        {userInfo && loanApplications && <LoanApplications userInfo={userInfo}
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

export default CustomerViewPage