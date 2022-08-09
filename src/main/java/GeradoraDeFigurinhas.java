import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {


    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
//        InputStream inputStream = new FileInputStream(new File("image/filme-grayman.jpg"));
//        InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BOWY4MmFiY2QtMzE1YS00NTg1LWIwOTQtYTI4ZGUzNWIxNTVmXkEyXkFqcGdeQXVyODk4OTc3MTY@.jpg").openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria uma nova imagem em memória com transparencia e tamanho novo

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);


        // copiar a imagem original para nova imagem em memória
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0,0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.MAGENTA);


        // escrever uma frase na nova imagem
        graphics.drawString("teste",360,novaAltura - 50);

        // salvar a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

        // cria uma nova imagem em memória com transparencia e tamanho novo

}
