
const ctx = document.getElementById('profitChart').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
        datasets: [
            {
                label: 'Revenue',
                data: [20000, 30000, 60000, 50000, 55000, 40000, 60000],
                borderColor: 'green',
                fill: false,
            },
            {
                label: 'Profit',
                data: [15000, 25000, 50000, 45000, 50000, 35000, 55000],
                borderColor: 'orange',
                fill: false,
            },
        ],
    },
    options: {
        scales: {
            y: {
                beginAtZero: true, // Bắt đầu từ 0
                min: 0, // Giới hạn giá trị nhỏ nhất của trục Y
                max: 80000, // Giới hạn giá trị lớn nhất của trục Y
                ticks: {
                    stepSize: 20000, // Bước nhảy giữa các giá trị
                },
            },
        },
    },
});
