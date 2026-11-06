// API base endpoints
const API_BASE = '/api';

// DOM Elements
const studentTableBody = document.getElementById('studentTableBody');
const alertBanner = document.getElementById('alertBanner');

// Modals
const studentModal = document.getElementById('studentModal');
const scoreModal = document.getElementById('scoreModal');

// Buttons
const btnOpenStudentModal = document.getElementById('btnOpenStudentModal');
const btnOpenScoreModal = document.getElementById('btnOpenScoreModal');
const closeStudentModal = document.getElementById('closeStudentModal');
const closeScoreModal = document.getElementById('closeScoreModal');
const cancelStudentBtn = document.getElementById('cancelStudentBtn');
const cancelScoreBtn = document.getElementById('cancelScoreBtn');

// Forms
const studentForm = document.getElementById('studentForm');
const scoreForm = document.getElementById('scoreForm');
const selectStudent = document.getElementById('selectStudent');
const selectSubject = document.getElementById('selectSubject');

// --- Initialization ---
document.addEventListener('DOMContentLoaded', () => {
    loadStudentScores();
    setupEventListeners();
});

function setupEventListeners() {
    // Open Student Modal
    btnOpenStudentModal.addEventListener('click', () => {
        studentForm.reset();
        studentModal.classList.remove('hidden');
        document.getElementById('studentCode').focus();
    });

    // Close Student Modal
    closeStudentModal.addEventListener('click', () => studentModal.classList.add('hidden'));
    cancelStudentBtn.addEventListener('click', () => studentModal.classList.add('hidden'));

    // Open Score Modal
    btnOpenScoreModal.addEventListener('click', async () => {
        scoreForm.reset();
        await loadStudentsDropdown();
        await loadSubjectsDropdown();
        scoreModal.classList.remove('hidden');
    });

    // Close Score Modal
    closeScoreModal.addEventListener('click', () => scoreModal.classList.add('hidden'));
    cancelScoreBtn.addEventListener('click', () => scoreModal.classList.add('hidden'));

    // Submit Student Form
    studentForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const payload = {
            studentCode: document.getElementById('studentCode').value.trim(),
            fullName: document.getElementById('fullName').value.trim(),
            address: document.getElementById('address').value.trim()
        };

        try {
            const res = await fetch(`${API_BASE}/students`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!res.ok) {
                const errData = await res.json();
                throw new Error(errData.message || 'Failed to insert student');
            }

            const data = await res.json();
            showAlert(`Student '${data.fullName}' (${data.studentCode}) inserted successfully!`, 'success');
            studentModal.classList.add('hidden');
            loadStudentScores();
        } catch (err) {
            showAlert(err.message, 'error');
        }
    });

    // Submit Score Form
    scoreForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const payload = {
            studentId: parseInt(selectStudent.value),
            subjectId: parseInt(selectSubject.value),
            score1: parseFloat(document.getElementById('score1').value),
            score2: parseFloat(document.getElementById('score2').value)
        };

        try {
            const res = await fetch(`${API_BASE}/scores`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!res.ok) {
                const errData = await res.json();
                throw new Error(errData.message || 'Failed to insert score');
            }

            const data = await res.json();
            showAlert(`Score for '${data.studentName}' in subject '${data.subjectName}' added! Grade: ${data.grade}`, 'success');
            scoreModal.classList.add('hidden');
            loadStudentScores();
        } catch (err) {
            showAlert(err.message, 'error');
        }
    });
}

// --- Data Fetching & Rendering ---

// 1. Load Bảng danh sách sinh viên & điểm (Question 3)
async function loadStudentScores() {
    try {
        const res = await fetch(`${API_BASE}/scores`);
        if (!res.ok) throw new Error('Cannot load score data');
        const list = await res.json();

        if (list.length === 0) {
            studentTableBody.innerHTML = `
                <tr>
                    <td colspan="9" class="text-center" style="padding: 24px; color: #64748b;">
                        No student score records found. Click <b>+ Student</b> and <b>+ Score</b> to add.
                    </td>
                </tr>
            `;
            return;
        }

        studentTableBody.innerHTML = list.map((item, index) => `
            <tr>
                <td>${index + 1}</td>
                <td>${escapeHtml(item.studentCode)}</td>
                <td>${escapeHtml(item.studentName)}</td>
                <td>${escapeHtml(item.subjectName)}</td>
                <td>${formatScore(item.score1)}</td>
                <td>${formatScore(item.score2)}</td>
                <td>${item.credit}</td>
                <td><b>${item.grade}</b></td>
                <td class="text-center">
                    <i class="fa-solid fa-pencil edit-icon" title="Edit Score" onclick="alert('Edit action for ${escapeHtml(item.studentName)} - ${escapeHtml(item.subjectName)}')"></i>
                </td>
            </tr>
        `).join('');
    } catch (err) {
        studentTableBody.innerHTML = `
            <tr>
                <td colspan="9" class="text-center" style="color: #dc2626; padding: 20px;">
                    Error loading records: ${err.message}
                </td>
            </tr>
        `;
    }
}

// 2. Load Dropdown Students cho Modal (+ Score)
async function loadStudentsDropdown() {
    try {
        const res = await fetch(`${API_BASE}/students`);
        if (!res.ok) throw new Error('Cannot load students');
        const students = await res.json();

        selectStudent.innerHTML = '<option value="">-- Choose Student --</option>' +
            students.map(s => `<option value="${s.studentId}">${escapeHtml(s.studentCode)} - ${escapeHtml(s.fullName)}</option>`).join('');
    } catch (err) {
        console.error('Error fetching students:', err);
    }
}

// 3. Load Dropdown Subjects cho Modal (+ Score)
async function loadSubjectsDropdown() {
    try {
        const res = await fetch(`${API_BASE}/subjects`);
        if (!res.ok) throw new Error('Cannot load subjects');
        const subjects = await res.json();

        selectSubject.innerHTML = '<option value="">-- Choose Subject --</option>' +
            subjects.map(sub => `<option value="${sub.subjectId}">${escapeHtml(sub.subjectCode)} - ${escapeHtml(sub.subjectName)} (${sub.credit} credits)</option>`).join('');
    } catch (err) {
        console.error('Error fetching subjects:', err);
    }
}

// --- Utilities ---
function formatScore(score) {
    if (score === null || score === undefined) return '-';
    return Number(score).toFixed(1);
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>"']/g, m => ({
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    })[m]);
}

function showAlert(message, type = 'success') {
    alertBanner.textContent = message;
    alertBanner.className = `alert-banner ${type === 'success' ? 'alert-success' : 'alert-error'}`;
    alertBanner.classList.remove('hidden');

    setTimeout(() => {
        alertBanner.classList.add('hidden');
    }, 4500);
}

