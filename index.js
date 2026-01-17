import express from 'express';
import bodyParser from 'body-parser';
import env from 'dotenv';
import pg from 'pg';

const app = express();
const port = 1304;
env.config();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

const db = new pg.Client({
    user: process.env.PG_USER,
    host: process.env.PG_HOST,
    database: process.env.PG_DATABASE,
    password: process.env.PG_PASSWORD,
    port: process.env.PG_PORT,
});
db.connect(); 

app.get('/', async (req, res) => {
    try {
        const resultEmp = await db.query('SELECT * FROM vw_employees');
        const resultRls = await db.query('SELECT * FROM department_roles');
        const employees = resultEmp.rows || [];
        const department_roles = resultRls.rows || [];
        // res.json(employees); if you want to see the data as JSON
        res.render('index.ejs', { employees, department_roles });
    } catch (error) {
        console.error('Error fetching employees:', error);
    }
});

app.post('/add-employee', async (req, res) => {
    const { employee_name, company_email, department_role_id, hire_date } = req.body;
    try {
        await db.query('INSERT INTO employees (employee_name, company_email, department_role_id, hire_date) VALUES ($1, $2, $3, $4)', [employee_name, company_email, department_role_id, hire_date]);
        res.redirect('/');
    } catch (error) {
        console.error('Error adding employee:', error);
    }
});

app.post('/update-employee', async (req, res) => {
    const { employee_id, employee_name, company_email, department_role_id, hire_date } = req.body;
    try {
        await db.query('UPDATE employees SET employee_name = $1, company_email = $2, department_role_id = $3, hire_date = $4 WHERE employee_id = $5', [employee_name, company_email, department_role_id, hire_date, employee_id]);
        res.redirect('/');
    } catch (error) {
        console.error('Error updating employee:', error);
    }
});

app.post('/delete-employee', async (req, res) => {
    const { employee_id } = req.body;
    try {
        await db.query('DELETE FROM employees WHERE employee_id = $1', [employee_id]);
        res.redirect('/');
    } catch (error) {
        console.error('Error deleting employee:', error);
    }
});

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});