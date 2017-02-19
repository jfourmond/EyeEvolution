csvDir <- "../resources/";
rapportDir <- "../../Rapport/";

# Récupération du nom des fichiers csv dans le répertoire "resources"
csvFiles <- list.files(path=csvDir, pattern="*.csv")

print(csvFiles[1])
print(length(csvFiles))

# Pour chague fichier csv...
for(csvFile in csvFiles) {
  # récupération du nom sans extension
  index <- regexpr(pattern ='.csv', csvFile)-1
  r <-substring(csvFile, 0, index)
  
  # ajout du répertoire au nom
  file <- paste(csvDir, csvFile, sep="", collapse=NULL)
  print(file)   
  
  # lecture du fichier
  data <- read.csv(file=file, header=TRUE, sep=";")
  
  print("Plot y = AverageCurveRadius(Generation) ")
  jpeg(file=paste(rapportDir, r, "_average_curve_radius.jpeg"))
  plot(data$generation, data$average.curve.radius, pch=3, xlab="generation", ylab="average curve radius", col="blue")
  lines(data$generation, data$best.eye.curve.radius, pch=3, col="red")
  dev.off()
  
  print("Plot y = AverageIrisSize(Generation)")
  jpeg(file=paste(rapportDir, r, "_average_iris_size.jpeg"))
  plot(data$generation, data$average.iris.size, pch=3, xlab="generation", ylab="iris size", col="blue")
  points(data$generation, data$best.eye.iris.size, pch=3, col="red")
  dev.off()
  
  print("Plot y = AverageRefractionIndex(Generation)")
  jpeg(file=paste(rapportDir, r, "_average_refraction_index.jpeg"))
  plot(data$generation, data$average.refraction.index, pch=3, xlab="generation", ylab="refraction index", col="blue")
  points(data$generation, data$best.eye.refraction.index, pch=3, col="red")
  dev.off()
  
  print("Plot y = AverageFitness(Generation)")
  jpeg(file=paste(rapportDir, r, "_average_fitness.jpeg"))
  plot(data$generation, data$average.fitness, pch=3, xlab="generation", ylab="average fitness", col="blue")
  points(data$generation, data$best.eye.fitness, pch=3, col="red")
  dev.off()
}