using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


[System.Serializable]
public class Question
{
    [TextArea(2, 5)] public string questionText;        // Soal (atas)
    [TextArea(2, 5)] public string[] options = new string[4]; // A..D
    public int correctIndex;                             // 0=A,1=B,2=C,3=D
}

public class QuizPage : MonoBehaviour
{
    [Header("UI: Konten")]
    public TextMeshProUGUI questionTMP;   // teks soal (atas)
    public TextMeshProUGUI optionsTMP;    // teks pilihan (A..D dalam 1 kotak)
    public TextMeshProUGUI progressTMP;   // "1/5"

    [Header("UI: Tombol Jawaban (A-D)")]
    public Button btnA;
    public Button btnB;
    public Button btnC;
    public Button btnD;

    [Header("Popup Benar/Salah")]
    public GameObject feedbackPanel;      // panel popup (nonaktif di awal)
    public Image feedbackImage;           // Image di panel
    public Sprite correctSprite;
    public Sprite wrongSprite;
    public float autoCloseDelay = 0.9f;   // jeda sebelum next soal

    [Header("Panel Hasil Akhir")]
    public GameObject resultPanel;        // nonaktif di awal
    public TextMeshProUGUI scoreText;     // "Skor: 80/100 (4/5)"
    public TextMeshProUGUI detailText;    // pesan motivasi

    [Header("Warna Tombol Saat Feedback")]
    public Color correctColor = new Color(0.70f, 1f, 0.70f);
    public Color wrongColor = new Color(1f, 0.70f, 0.70f);

    [Header("Navigasi")]
    public GameObject navigationPanel; // object Navigation, drag di Inspector
    public Button nextButton;          // tombol Next di dalam Navigation
    public Button restartButton;       // tombol Restart di dalam Navigation 

    [Header("Data Soal")]
    public List<Question> questions = new List<Question>();

    [Header("Randomization")] // [RANDOMIZE]
    public bool shuffleQuestions = true;   // acak urutan soal tiap sesi
    public bool shuffleOptions = true;   // acak urutan opsi A–D tiap soal


    int index = 0;         // soal aktif
    int score = 0;         // jumlah benar
    bool locked = false;   // cegah double click saat popup
    Image aImg, bImg, cImg, dImg;
    Color a0, b0, c0, d0;

    


    void Awake()
    {
        // binding tombol -> Answer(0..3)
        btnA.onClick.AddListener(() => OnAnswer(0));
        btnB.onClick.AddListener(() => OnAnswer(1));
        btnC.onClick.AddListener(() => OnAnswer(2));
        btnD.onClick.AddListener(() => OnAnswer(3));

        // cache warna asli tombol
        aImg = btnA.GetComponent<Image>(); bImg = btnB.GetComponent<Image>();
        cImg = btnC.GetComponent<Image>(); dImg = btnD.GetComponent<Image>();
        a0 = aImg.color; b0 = bImg.color; c0 = cImg.color; d0 = dImg.color;

        // contoh soal jika list kosong
        if (questions.Count == 0) LoadSample();

        if (feedbackPanel) feedbackPanel.SetActive(false);
        if (resultPanel) resultPanel.SetActive(false);
    }

    void Start()
    {
        // [RANDOMIZE] — lakukan pengacakan di awal sesi
        RandomizeQuestionsAndOptions();

        if (nextButton) nextButton.gameObject.SetActive(false); // sembunyikan Next saat mulai
        if (restartButton) restartButton.gameObject.SetActive(false); // sembunyikan Next saat mulai
        ShowQuestion();
    }

    // [RANDOMIZE] — fungsi utama pengacakan
    void RandomizeQuestionsAndOptions()
    {
        if (shuffleQuestions) ShuffleList(questions);

        if (shuffleOptions)
        {
            for (int i = 0; i < questions.Count; i++)
            {
                Question q = questions[i];       // ambil
                ShuffleOptions(q);               // acak opsinya
                questions[i] = q;                // simpan balik
            }
        }
    }


    void ShowQuestion()
    {
        locked = false;
        ResetButtonColors();
        SetButtonsInteractable(true);

        var q = questions[index];
        if (questionTMP) questionTMP.text = q.questionText;

        if (optionsTMP)
            optionsTMP.text =
                $"A. {q.options[0]}\n" +
                $"B. {q.options[1]}\n" +
                $"C. {q.options[2]}\n" +
                $"D. {q.options[3]}";

        if (progressTMP) progressTMP.text = $"{index + 1}/{questions.Count}";
    }

    void OnAnswer(int pickedIndex)
    {
        if (locked) return;
        locked = true;

        int correctIdx = questions[index].correctIndex;
        bool isRight = (pickedIndex == correctIdx);
        if (isRight) score++;

        // warna tombol yang dipilih + yang benar (jika salah)
        PaintButton(pickedIndex, isRight ? correctColor : wrongColor);
        if (!isRight) PaintButton(correctIdx, correctColor);

        SetButtonsInteractable(false);
        ShowFeedback(isRight);
    }

    void ShowFeedback(bool isCorrect)
    {
        if (feedbackPanel && feedbackImage)
        {
            feedbackImage.sprite = isCorrect ? correctSprite : wrongSprite;
            feedbackPanel.SetActive(true);
        }
        StartCoroutine(AutoNext());
    }

    IEnumerator AutoNext()
    {
        yield return new WaitForSecondsRealtime(autoCloseDelay);

        if (feedbackPanel) feedbackPanel.SetActive(false);

        index++;
        if (index < questions.Count)
        {
            ShowQuestion();
        }
        else
        {
            ShowResult();
        }
        locked = false;
    }

    void ShowResult()
    {
        // sembunyikan konten kuis
        SetButtonsInteractable(false);

        if (resultPanel) resultPanel.SetActive(true);

        int score100 = Mathf.RoundToInt((score / (float)questions.Count) * 100f);
        if (scoreText) scoreText.text = $"{score100}";
        if (detailText)
        {
            detailText.text = score100 >= 80 ? "Mantap!!!"
                              : score100 >= 60 ? "Bagus, lanjutkan latihan!"
                              : "Coba lagi, kamu pasti bisa!";
        }

        // aktifkan tombol Next hanya di panel result
        if (nextButton)
        {
            nextButton.gameObject.SetActive(true);
            nextButton.onClick.RemoveAllListeners();
            nextButton.onClick.AddListener(() => BackToMenu("QuizMenu")); // ganti sesuai nama scene
            nextButton.GetComponentInChildren<TextMeshProUGUI>()?.SetText("Menu");
        }
        if (restartButton)
        {
            restartButton.gameObject.SetActive(true);
            restartButton.onClick.RemoveAllListeners();
            restartButton.onClick.AddListener(RestartQuiz);
        }
    }


    // ===== Util =====
    void SetButtonsInteractable(bool v)
    {
        btnA.interactable = v; btnB.interactable = v;
        btnC.interactable = v; btnD.interactable = v;
    }

    void PaintButton(int idx, Color c)
    {
        switch (idx)
        {
            case 0: aImg.color = c; break;
            case 1: bImg.color = c; break;
            case 2: cImg.color = c; break;
            case 3: dImg.color = c; break;
        }
    }

    void ResetButtonColors()
    {
        aImg.color = a0; bImg.color = b0; cImg.color = c0; dImg.color = d0;
    }

    // Fisher–Yates Shuffle untuk List<T>
    void ShuffleList<T>(IList<T> list)
    {
        for (int i = list.Count - 1; i > 0; i--)
        {
            int j = Random.Range(0, i + 1); // inclusive
            (list[i], list[j]) = (list[j], list[i]);
        }
    }

    // [RANDOMIZE] — acak urutan opsi A–D dan perbarui correctIndex
    void ShuffleOptions(Question q)
    {
        if (q.options == null || q.options.Length < 4) return;

        int[] map = { 0, 1, 2, 3 };
        for (int i = map.Length - 1; i > 0; i--)
        {
            int j = Random.Range(0, i + 1);
            (map[i], map[j]) = (map[j], map[i]);
        }

        var newOpts = new string[4];
        int newCorrect = -1;
        for (int i = 0; i < 4; i++)
        {
            newOpts[i] = q.options[map[i]];
            if (map[i] == q.correctIndex) newCorrect = i;
        }
        q.options = newOpts;
        q.correctIndex = newCorrect;
    }



    // Contoh 5 soal
    void LoadSample()
    {
        questions = new List<Question>()
        {
            new Question{
                questionText="Vektor adalah besaran yang memiliki…",
                options=new[]{ "Nilai saja", "Arah saja", "Nilai dan arah", "Satuan dan waktu" },
                correctIndex=2
            },
            new Question{
                questionText="Besaran skalar adalah besaran yang memiliki…",
                options=new[]{ "Arah dan nilai", "Nilai saja", "Arah saja", "Tidak punya satuan" },
                correctIndex=1
            },
            new Question{
                questionText="Contoh besaran vektor adalah…",
                options=new[]{ "Massa", "Suhu", "Kecepatan", "Waktu" },
                correctIndex=2
            },
            new Question{
                questionText="Resultan dua vektor searah adalah…",
                options=new[]{ "Selisih nilainya", "Jumlah nilainya", "Nol", "Arah berubah 90°" },
                correctIndex=1
            },
            new Question{
                questionText="Simbol yang benar untuk vektor gaya adalah…",
                options=new[]{ "F (tanpa panah)", "F⃗", "m", "t" },
                correctIndex=1
            },
        };
    }

    // Panggil ini dari tombol "Menu" di result panel
    public void BackToMenu(string menuSceneName)
    {
        SceneManager.LoadScene("QuizMenu");
    }

    // Opsional: panggil dari tombol "Restart"
    // Panggil ini dari tombol "Restart" di result panel
    public void RestartQuiz()
    {
        index = 0;
        score = 0;
        locked = false;

        // Tutup feedback & result
        if (feedbackPanel) feedbackPanel.SetActive(false);
        if (resultPanel) resultPanel.SetActive(false);

        // Sembunyikan Next (Menu) dan bersihkan handler/label
        if (nextButton)
        {
            nextButton.onClick.RemoveAllListeners();
            nextButton.GetComponentInChildren<TextMeshProUGUI>()?.SetText("Next");
            nextButton.gameObject.SetActive(false); // <— penting: hilangkan saat kembali ke mode kuis
        }

        // (Opsional) sembunyikan reset bila kamu aktifkan manual di luar resultPanel
        if (restartButton)
            restartButton.gameObject.SetActive(false);

        // Pulihkan tombol jawaban & warna
        ResetButtonColors();
        SetButtonsInteractable(true);

        // [RANDOMIZE] — acak ulang untuk sesi baru
        RandomizeQuestionsAndOptions();


        // Tampilkan soal pertama lagi
        ShowQuestion();
    }
}
