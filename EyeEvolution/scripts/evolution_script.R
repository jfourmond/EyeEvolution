data <- read.table("../resources/1487077054651_evolution.csv", h=T, sep=";")
data$X <- NULL

plot(data$generation, data$average.curve.radius, pch=3, xlab="generation", ylab="average curve radius", col="blue")
points(data$generation, data$best.eye.curve.radius, pch=3, col="red")

plot(data$generation, data$average.iris.size, pch=3, xlab="generation", ylab="iris size", col="blue")
points(data$generation, data$best.eye.iris.size, pch=3, col="red")

plot(data$generation, data$average.angle, pch=3, xlab="generation", ylab="angle", col="blue")
points(data$generation, data$best.eye.angle, pch=3, col="red")

plot(data$generation, data$average.refraction.index, pch=3, xlab="generation", ylab="refraction index", col="blue")
points(data$generation, data$best.eye.refraction.index, pch=3, col="red")

plot(data$generation, data$average.fitness, pch=3, xlab="generation", ylab="average fitness", col="blue")
points(data$generation, data$best.eye.fitness, pch=3, col="red")

print(data)