// Sales & Purchase Chart
var ctx = document.getElementById('salesChart').getContext('2d');
var salesChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'May', 'Jun'],
        datasets: [{
            label: 'Sales',
            data: [40000, 50000, 35000, 45000, 30000, 55000, 10000, 29000, 20000, 54000],
            backgroundColor: '#7a9af7'
        }, {
            label: 'Purchase',
            data: [30000, 40000, 25000, 35000, 20000, 40000, 30000, 9000, 42000, 38000],
            backgroundColor: '#54d361'
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Order Summary Chart
var ctx2 = document.getElementById('orderChart').getContext('2d');
var orderChart = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
        datasets: [{
            label: 'Ordered',
            data: [3500, 900, 2500, 1500, 2100],
            borderColor: 'rgb(219, 163, 98)',
            fill: false
        }, {
            label: 'Delivered',
            data: [3100, 1000, 3900, 2500, 2000],
            borderColor: 'rgba(54, 162, 235, 1)',
            fill: false
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});