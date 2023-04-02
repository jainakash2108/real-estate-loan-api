const {createProxyMiddleware} = require('http-proxy-middleware');

const LoanApplicationsMock = require('../src/mock/LoanApplications.json')
const UserDetailsMock = require('../src/mock/UserDetails.json')
const AdminDetailsMock = require('../src/mock/AdminDetails.json')

const setupMock = () => {
    console.log('setting up mock')
    module.exports = (app) => {
        app.get(`/loan-api/api/v1/user/info`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200).json(Math.random() > 0.5 ? UserDetailsMock : AdminDetailsMock)
        })

        app.get(`/loan-api/api/v1/loan/view/applications`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200).json(Math.random() > 0.5 ? LoanApplicationsMock : [])
        })

        app.put(`/loan-api/api/v1/loan/review/:loanId`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })

        app.put(`/loan-api/api/v1/loan/approve/:loanId`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })

        app.put(`/loan-api/api/v1/loan/reject/:loanId`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })

        app.put(`/loan-api/api/v1/loan/withdraw/:loanId`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })

        app.post(`/loan-api/api/v1/loan/apply`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })

        app.post(`/loan-api/api/v1/user/register`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })

        app.post(`/loan-api/api/v1/admin/register`, (req, res) => {
            const error = Math.random() > 0.5
            error ? res.status(500).json('Something went wrong') : res.status(200)
        })
    }
}

const setupBackendProxy = () => {
    console.log('setting up backendProxy')
    module.exports = function (app) {
        app.use(
            '/',
            createProxyMiddleware({
                target: 'http://localhost:8089/',
                changeOrigin: true,
            }),
        )
    }
}

const PROXY_MODE = process.env.PROXY_MODE

if (PROXY_MODE !== 'mock' && PROXY_MODE !== 'backend')
    throw new Error(
        "PROXY_MODE needs to be set with either 'mock' or 'backend'",
    )

if (PROXY_MODE === 'mock') setupMock()
else if (PROXY_MODE === 'backend') setupBackendProxy()
