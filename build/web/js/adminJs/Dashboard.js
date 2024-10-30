document.addEventListener("DOMContentLoaded", () => {
        const url = 'http://localhost:8080/FreshFoodStore/admin/AdChartSalesServlet'; // Đường dẫn servlet

        fetch(url)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Lấy nhãn (ngày) và dữ liệu doanh thu, giá vốn từ dữ liệu JSON
        const labels = data.revenue.map(item => item.date);
        const revenueData = data.revenue.map(item => item.value);
        const costData = data.cost.map(item => item.value);

        // Tạo biểu đồ nếu dữ liệu nhận được hợp lệ
        if (labels.length > 0 && revenueData.length > 0 && costData.length > 0) {
            const ctx = document.getElementById('salesChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: 'Doanh thu',
                            data: revenueData,
                            backgroundColor: '#7a9af7'
                        },
                        {
                            label: 'Giá vốn',
                            data: costData,
                            backgroundColor: '#54d361'
                        }
                    ]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'VNĐ'
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Ngày'
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            display: true,
                            position: 'top'
                        },
                        tooltip: {
                            enabled: true
                        }
                    }
                }
            });
        } else {
            console.error("No data available to display the chart.");
        }
    })
    .catch(error => console.error('Error fetching data:', error));

    });

document.addEventListener("DOMContentLoaded", () => {
    const url = 'http://localhost:8080/FreshFoodStore/admin/AdChartOrdersServlet'; // Đường dẫn servlet

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Lấy nhãn (ngày) và dữ liệu đơn hàng đã đặt, đã giao từ dữ liệu JSON
            const labels = data.orders.map(item => item.date); 
            const orderedData = data.orders.map(item => item.value); 
            const deliveredData = data.delivered.map(item => item.value); 

            // Tạo biểu đồ nếu dữ liệu nhận được hợp lệ
            if (labels.length > 0 && orderedData.length > 0 && deliveredData.length > 0) {
                const ctx = document.getElementById('orderChart').getContext('2d');
                new Chart(ctx, {
                    type: 'line', 
                    data: {
                        labels: labels,
                        datasets: [
                            {
                                label: 'Đơn hàng đã đặt',
                                data: orderedData,
                                borderColor: '#7a9af7',
                                fill: false
                            },
                            {
                                label: 'Đơn hàng đã giao',
                                data: deliveredData,
                                borderColor: '#54d361',
                                fill: false
                            }
                        ]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true,
                                title: {
                                    display: true,
                                    text: 'Số lượng đơn hàng'
                                }
                            },
                            x: {
                                title: {
                                    display: true,
                                    text: 'Ngày'
                                }
                            }
                        },
                        plugins: {
                            legend: {
                                display: true,
                                position: 'top'
                            },
                            tooltip: {
                                enabled: true
                            }
                        }
                    }
                });
            } else {
                console.error("No data available to display the chart.");
            }
        })
        .catch(error => console.error('Error fetching data:', error));
});
