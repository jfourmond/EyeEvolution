csvDir <- "../resources/";

# Récupération du nom des fichiers csv dans le répertoire "resources"
print('Récupération du nom des fichiers csv dans le répertoire "resources"')
csvFiles <- list.files(path=csvDir, pattern="*.csv")

# Concanténation du nom répertoire comme préfixe au nom des fichiers
for(i in 1:length(csvFiles)) {
  csvFiles[i] <- paste(csvDir, csvFiles[i], sep="", collapse=NULL)
}

# Lecture des fichiers dans une liste de dataframes
print('Lecture des fichiers dans une liste de dataframes')
datas <- lapply(csvFiles, read.csv, h=T, sep=";")

# Concaténation en un seul gros dataframe
print('Concaténation en un seul gros dataframe')
data <- do.call(rbind, datas)

# Suppression de la colonne X, inconnue au bataillon
print('Suppression de la colonne X')
data$X <- NULL

# Plot 1
print('Plot 1')
jpeg(file="plot_crossoverrate_fitness.jpeg")
plot(data$cross.over.rate, data$best.eye.fitness, pch=3, xlab="crossover rate", ylab="best eye fitness")
summary(lm(data$best.eye.fitness ~ data$cross.over.rate))
abline(lm(data$best.eye.fitness ~ data$cross.over.rate))
dev.off()

# Plot 2
print('Plot 2')
jpeg(file="plot_mutationrate_fitness.jpeg")
plot(data$mutation.rate, data$best.eye.fitness, pch=3, xlab="mutation rate", ylab="best eye fitness")
summary(lm(data$best.eye.fitness ~ data$mutation.rate))
abline(lm(data$best.eye.fitness ~ data$mutation.rate))
dev.off()

# Plot 3
print('Plot 3')
jpeg(file="plot_populationsize_fitness.jpeg")
plot(data$population.size, data$best.eye.fitness, pch=3, xlab="population size", ylab="best eye fitness")
summary(lm(data$best.eye.fitness ~ data$population.size))
abline(lm(data$best.eye.fitness ~ data$population.size))
dev.off()