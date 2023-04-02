export interface UserInfo {
    name: string
    email: string
    customerId: string,
    role: string
}

export interface UserRequest {
    name: string
    email: string
    customerSSN: string
    password: string
}

export interface LoanRequest {
    loanAmount: string
    equityAmount: string
    salaryAmount: string
}

export interface LoanApplicationsData {
    applications: LoanApplicationData[]
}

export interface LoanApplicationData {
    id: number
    loanAmount: string
    equityAmount: string
    salaryAmount: string
    status: LoanStatus[]
}

export interface LoanStatus {
    status: string
    description: string
    createdAt: string
}

export interface ErrorResponse {
    message: string
}