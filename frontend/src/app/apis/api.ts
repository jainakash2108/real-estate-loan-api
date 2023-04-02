import {LoanApplicationData, LoanRequest, UserInfo, UserRequest} from "../types/DataTypes";

const headers = {
    "Content-Type": "application/json",
    Accept: "application/json",
};

export async function fetchLoanApplications() {
    const response = await fetch("/loan-api/api/v1/loan/view/applications", {
        method: "GET",
        headers: headers,
    });
    if (response.ok) {
        const data: LoanApplicationData[] = await response.json();
        return data;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function fetchLoggedInUserDetails() {
    const response = await fetch("/loan-api/api/v1/user/info", {
        method: "GET",
        headers: headers,
    });
    if (response.ok) {
        const data: UserInfo = await response.json();
        return data;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function registerUser(userRequest: UserRequest) {
    const response = await fetch("/loan-api/api/v1/user/register", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(userRequest),
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function registerAdmin(userRequest: UserRequest) {
    const response = await fetch("/loan-api/api/v1/admin/register", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(userRequest),
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function submitLoanApplication(loanRequest: LoanRequest) {
    const response = await fetch("/loan-api/api/v1/loan/apply", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(loanRequest),
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function startReview(loanId: number) {
    const response = await fetch(`/loan-api/api/v1/loan/review/${loanId}`, {
        method: "PUT",
        headers: headers,
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function approveLoanApplication(loanId: number) {
    const response = await fetch(`/loan-api/api/v1/loan/approve/${loanId}`, {
        method: "PUT",
        headers: headers,
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function rejectLoanApplication(loanId: number) {
    const response = await fetch(`/loan-api/api/v1/loan/reject/${loanId}`, {
        method: "PUT",
        headers: headers,
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}

export async function withdrawLoanApplication(loanId: number) {
    const response = await fetch(`/loan-api/api/v1/loan/withdraw/${loanId}`, {
        method: "PUT",
        headers: headers,
    });
    if (response.ok) {
        return;
    } else {
        const errorResponse = await response.json(); // get error response as JSON
        throw new Error(errorResponse.message);
    }
}
